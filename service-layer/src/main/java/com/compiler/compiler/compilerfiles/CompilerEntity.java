package com.compiler.compiler.compilerfiles;

public class CompilerEntity {
    String code;
    String userId;
    String language;
    String extension;

    public CompilerEntity() {

    }

    public CompilerEntity(String code, String userId, String language, String extension) {
        super();
        this.code = code;
        this.userId = userId;
        this.language = language;
        this.extension = extension;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
