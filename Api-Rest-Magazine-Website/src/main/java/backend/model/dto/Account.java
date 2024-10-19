/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

/**
 *
 * @author brigidoalvarado
 */
public class Account {
    
    private Credential credential = new Credential();
    private Profile profile = new Profile();
    private ApiFile apiFile = new ApiFile();

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ApiFile getApiFile() {
        return apiFile;
    }

    public void setApiFile(ApiFile apiFile) {
        this.apiFile = apiFile;
    }    
}
