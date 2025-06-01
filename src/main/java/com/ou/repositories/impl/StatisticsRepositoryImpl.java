package com.ou.repositories.impl;

import com.ou.dto.CourseRevenueStatisticsDto;
import com.ou.dto.RevenueStatisticsDto;
import com.ou.dto.UserRoleStatisticsDto;
import com.ou.repositories.StatisticsRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StatisticsRepositoryImpl implements StatisticsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<UserRoleStatisticsDto> getUserRoleStatistics() {
        String jpql =   "SELECT new com.ou.dto.UserRoleStatisticsDto(r.name, COUNT(u.id)) " +
                        "FROM User u JOIN u.userRoleId r " +
                        "GROUP BY r.name";

        Session session = factory.getObject().getCurrentSession();
        List<UserRoleStatisticsDto> result = session.createQuery(jpql, UserRoleStatisticsDto.class).getResultList();
        return result;
    }

    @Override
    public List<RevenueStatisticsDto> getMonthlyRevenueStatistics(int year) {
        String hql = """
        SELECT new com.ou.dto.RevenueStatisticsDto(
            YEAR(pr.createdAt), 
            MONTH(pr.createdAt), 
            SUM(pr.amount), 
            COUNT(pr.id)
        )
        FROM PaymentReceipt pr
        WHERE pr.status = 'succeeded' 
        AND (:year IS NULL OR YEAR(pr.createdAt) = :year)
        GROUP BY YEAR(pr.createdAt), MONTH(pr.createdAt)
        ORDER BY YEAR(pr.createdAt) DESC, MONTH(pr.createdAt) DESC
    """;

        return factory.getObject().getCurrentSession()
                .createQuery(hql, RevenueStatisticsDto.class)
                .setParameter("year", year)
                .getResultList();
    }

    @Override
    public List<RevenueStatisticsDto> getYearlyRevenue() {
        String hql = """
            SELECT new com.ou.dto.RevenueStatisticsDto(
                YEAR(pr.createdAt), 
                null, 
                SUM(pr.amount), 
                COUNT(pr.id)
            )
            FROM PaymentReceipt pr 
            WHERE pr.status = 'succeeded'
            GROUP BY YEAR(pr.createdAt)
            ORDER BY YEAR(pr.createdAt) DESC
        """;

        return factory.getObject().getCurrentSession()
                .createQuery(hql, RevenueStatisticsDto.class)
                .getResultList();
    }

    @Override
    public List<CourseRevenueStatisticsDto> getCourseRevenueStatistics() {
        String hql = """
        SELECT new com.ou.dto.CourseRevenueStatisticsDto(
            CAST(c.id AS Integer),
            c.name,
            c.categoryId.name,
            CAST(COUNT(pr.id) AS Integer),
            CAST(COALESCE(SUM(pr.amount), 0) AS java.math.BigDecimal)
        )
        FROM Course c
        LEFT JOIN PaymentReceiptCourse prc ON c.id = prc.course.id
        LEFT JOIN PaymentReceipt pr ON prc.paymentReceipt.id = pr.id AND pr.status = 'succeeded'
        GROUP BY c.id, c.name, c.categoryId.name
        ORDER BY COALESCE(SUM(pr.amount), 0) DESC
    """;

        return factory.getObject()
                .getCurrentSession()
                .createQuery(hql, CourseRevenueStatisticsDto.class)
                .getResultList();
    }

    @Override
    public List<RevenueStatisticsDto> getLast10YearsRevenueStatistics() {
        String hql = """
            SELECT new com.ou.dto.RevenueStatisticsDto(
                YEAR(pr.createdAt), 
                MONTH(pr.createdAt), 
                SUM(pr.amount), 
                COUNT(pr.id)
            )
            FROM PaymentReceipt pr
            WHERE pr.status = 'succeeded' 
            AND YEAR(pr.createdAt) >= (YEAR(CURRENT_DATE) - 9)
            GROUP BY YEAR(pr.createdAt), MONTH(pr.createdAt)
            ORDER BY YEAR(pr.createdAt) DESC, MONTH(pr.createdAt) DESC
        """;

        return factory.getObject().getCurrentSession()
                .createQuery(hql, RevenueStatisticsDto.class)
                .getResultList();
    }
}
