package sectors.sectors.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import sectors.sectors.dto.CreateUserSectorsDto;
import sectors.sectors.dto.UserSectorDto;
import sectors.sectors.service.SectorsBusinessService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequiredArgsConstructor
public class SectorController {

    private static final String USER_ID = "userId";
    private static final String SUCCESS_MESSAGE = "successMessage";
    private static final String ERROR_MESSAGE = "errorMessage";

    private final SectorsBusinessService service;

    @GetMapping("/")
    public String get(Model model, HttpSession session, HttpServletRequest request) {
        model.addAttribute("sectors", service.getAllSectors());
        Long userId = (Long) session.getAttribute(USER_ID);

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (userId != null) {
            model.addAttribute("user", service.getById(userId));
            if (inputFlashMap != null) {
                model.addAttribute(SUCCESS_MESSAGE, inputFlashMap.get(SUCCESS_MESSAGE));
            }
        }

        if (inputFlashMap != null) {
            model.addAttribute(ERROR_MESSAGE, inputFlashMap.get(ERROR_MESSAGE));
        }

        return "index";
    }

    @PostMapping("/")
    public RedirectView create(HttpServletRequest request, HttpSession session, RedirectAttributes attributes) {
        String name = request.getParameter("name");
        if (!hasText(name)) {
            attributes.addFlashAttribute(ERROR_MESSAGE, "Name is required");
            return new RedirectView("/");
        }
        String[] sectorsArray = request.getParameterValues("sectors");
        if (sectorsArray == null) {
            attributes.addFlashAttribute(ERROR_MESSAGE, "At least one sector is required");
            return new RedirectView("/");
        }

        String parameter = request.getParameter("agreedToTerms");
        boolean agreedToTerms = hasText(parameter) && parameter.equals("on");
        if (!agreedToTerms) {
            attributes.addFlashAttribute(ERROR_MESSAGE, "You must agree to terms");
            return new RedirectView("/");
        }

        List<Long> sectors = new ArrayList<>();
        for (String s : sectorsArray) {
            Long sectorId = Long.parseLong(s);
            sectors.add(sectorId);
        }

        Long userId = (Long) session.getAttribute(USER_ID);

        CreateUserSectorsDto dto = CreateUserSectorsDto.builder()
                .id(userId)
                .name(name)
                .sectors(sectors)
                .agreedToTerms(agreedToTerms)
                .build();
        UserSectorDto createdUser = service.create(dto);

        session.setAttribute(USER_ID, createdUser.getId());
        attributes.addFlashAttribute(SUCCESS_MESSAGE, "Sectors saved successfully!");

        return new RedirectView("/");
    }

}
