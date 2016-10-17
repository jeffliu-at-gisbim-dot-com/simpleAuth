/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisbim.simpleAuthWeb;

import com.gisbim.simpleAuthWeb.persist.Fun;
import com.gisbim.simpleAuthWeb.persist.PriUser;
import com.gisbim.simpleAuthWeb.persist.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jeff
 */
public class UserInfo {

    private static final long serialVersionUID = 1L;
    private PriUser user = null;
    private List<Role> roles = null;
    private Map<Role, List<Fun>> roleWithFunction;
    private String clientIp = null;

    public UserInfo() {
        this.roleWithFunction = new HashMap<>();
    }

    public PriUser getUser() {
        return user;
    }

    public void setUser(PriUser user) {
        this.user = user;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Map<Role, List<Fun>> getRoleWithFunction() {
        return roleWithFunction;
    }

    public void setRoleWithFunction(Map<Role, List<Fun>> roleWithFunction) {
        this.roleWithFunction = roleWithFunction;
    }

}
