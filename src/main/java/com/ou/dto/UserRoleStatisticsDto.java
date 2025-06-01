package com.ou.dto;

public class UserRoleStatisticsDto {
    private String roleName;
    private Long userCount;

    public UserRoleStatisticsDto() {}

    public UserRoleStatisticsDto(String roleName, Long userCount) {
        this.roleName = roleName;
        this.userCount = userCount;
    }

    // Getters and Setters
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public Long getUserCount() { return userCount; }
    public void setUserCount(Long userCount) { this.userCount = userCount; }
}
