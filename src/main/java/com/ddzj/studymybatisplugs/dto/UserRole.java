package com.ddzj.studymybatisplugs.dto;

public class UserRole implements java.io.Serializable{
    private String id;
    private String account;
    private String name;

    private String aliasName;

    private String roleCode;

    private String roleCodeName;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleCodeName() {
        return roleCodeName;
    }

    public void setRoleCodeName(String roleCodeName) {
        this.roleCodeName = roleCodeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
