package com.example.securitymvctemplate.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RoleAuthoritiesRequestModel {
    private String name;
    private List<String> authorityIds;
}
