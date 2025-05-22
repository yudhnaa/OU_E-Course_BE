package com.ou.controllers.webController.adminController;

import com.ou.dto.LecturerDto;
import com.ou.exceptions.NotFoundException;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.LecturerMapper;
import com.ou.pojo.*;
import com.ou.services.LecturerService;
import com.ou.services.LocalizationService;
import com.ou.utils.Pagination;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class LecturerController {

    private final LecturerService lecturerService;
    private final PaginationHelper paginationHelper;
    private final LecturerMapper lecturerMapper;
    private final LocalizationService localizationService;

    public LecturerController(LecturerService lecturerService, PaginationHelper paginationHelper, LecturerMapper lecturerMapper, LocalizationService localizationService) {
        this.lecturerService = lecturerService;
        this.paginationHelper = paginationHelper;
        this.lecturerMapper = lecturerMapper;
        this.localizationService = localizationService;
    }

    @GetMapping("/lecturers")
    public String lecturers(
            Model model,
            @RequestParam Map<String,String> params,
            RedirectAttributes redirectAttributes
    ) {
        // Get the total number of lecturers

        long totalItems;
        if (params.get("name") != null) {
            totalItems = lecturerService.countSearchResults(params);
        } else {
            totalItems = lecturerService.countLecturers();
        }

        Pagination pagination = paginationHelper.getPagination(params, totalItems);

        List<LecturerDto> lecturers = lecturerService.getLecturers(params).stream().map(l ->
                lecturerMapper.toDto(l)).collect(Collectors.toList()
        );

        if (lecturers.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lecture.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/lecturers";
        }

        model.addAttribute("lecturers", lecturers);

        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages() > 0 ? pagination.getTotalPages() : 1);
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("filterName", params.getOrDefault("name", ""));


        return "/dashboard/admin/lecturer/lecturer_list";
    }

    @GetMapping("/lecturer/{id}")
    public String courseDetail(Model model,
                               @PathVariable("id") int id,
                               @RequestParam Map<String, String> params
    ) throws Exception {

        Lecturer lecturer = lecturerService.getLecturerById(id)
                .orElseThrow(() -> new NotFoundException(localizationService.getMessage("lecture.notFound", LocaleContextHolder.getLocale())));

        model.addAttribute("lecturer", lecturer);

        return "dashboard/admin/lecturer/lecturer_detail";
    }
}
