package com.example.securitymvctemplate.role;

import com.example.securitymvctemplate.authority.AuthorityTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    private final AuthorityTitleRepository authorityTitleRepository;

//    @GetMapping(value = "/role")
//    public String getAddRoleForm(Model model) {
//        model.addAttribute("role", new Role());
//        model.addAttribute("authorityTitles", authorityTitleRepository.findAll());
//        return "add-role.html";
//    }
//
//    @PostMapping(value = "/role")
//    public ResponseEntity<HttpStatus> addRole(@RequestBody RoleAuthoritiesRequestModel requestModel) {
//        roleService.addRole(requestModel);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/role/view")
//    public String getAllRoles(Model model) {
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "roles.html";
//    }
//
//    @GetMapping(value = "/role/{roleId}")
//    public String editRoleForm(@PathVariable String roleId, Model model) {
//        model.addAttribute("authorityTitles", authorityTitleRepository.findAll());
//        model.addAttribute("role", roleService.getRole(UUID.fromString(roleId)));
//        model.addAttribute("roleAuthorityIds", roleService.getRole(UUID.fromString(roleId)).getAuthorities().stream().map(AuthorityEntity::getId).collect(Collectors.toList()));
//        return "edit-role";
//    }
//    @PostMapping(value = "/role/{roleId}")
//    public String editRole(Role newRole, @PathVariable String roleId) {
//        roleService.editRole(newRole, roleId);
//        return "redirect:/role/view";
//    }
}
