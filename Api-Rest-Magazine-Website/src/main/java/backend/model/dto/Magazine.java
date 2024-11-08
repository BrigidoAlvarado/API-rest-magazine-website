/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.dto;

import backend.exception.InvalidDataException;
import backend.model.Tag;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author brigidoalvarado
 */
public class Magazine {
    
    private boolean commentStatus;
    private boolean subscriptionStatus;
    private boolean isLiked;
    private double dailyCost;
    private int likes;
    private int id;
    private String title;
    private String category;
    private LocalDate date;
    private String description;
    private String editor;
    private String[] tags;
    private List<String> tagsList;
    private List<Integer> idFilesList;
    private ApiFile file;
    private List<Profile> subscriberList;
    private List<String> comments;

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
    
    public List<Integer> getIdFilesList() {
        return idFilesList;
    }

    public void setIdFilesList(List<Integer> idFilesList) {
        this.idFilesList = idFilesList;
    }

    public List<Profile> getSubscriberList() {
        return subscriberList;
    }

    public void setSubscriberList(List<Profile> subscriberList) {
        this.subscriberList = subscriberList;
    }
    
    
    
    public List<String> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<String> tagsList) {
        this.tagsList = tagsList;
        
    }

    public ApiFile getFile() {
        return file;
    }

    public void setFile(ApiFile file) {
        this.file = file;
    }

    
    public boolean isCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(boolean commentStatus) {
        this.commentStatus = commentStatus;
    }

    public boolean isSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public boolean isIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public double getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return title;
    }

    public void setTittle(String tittle) {
        this.title = tittle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = generateTags(tags);
    }
    
    public void setTags(List<String> tags){
        String[] tagss = new String[tags.size()];
        for (int i = 0; i < tagss.length; i++) {
            tagss[i] = tags.get(i);
        }
        this.tags = tagss;
    }
    
    public void validate() throws InvalidDataException{
        if (
                title == null || 
                category == null || 
                description == null || 
                tags == null || 
                date == null || 
                file == null || 
                editor == null) {
            throw new InvalidDataException("La revista no contiene toda la informacion necesaria para su manejo");
        }
        file.validate();
    }
    
    public void validateCost() throws InvalidDataException{
        if(dailyCost < 0 && id < 0){
            throw new InvalidDataException("Costo diario asignado invalido");
        }
    }
    
    public void validateSubscription()throws InvalidDataException{
        if(date == null || id < 0 ){
            throw new InvalidDataException("Datos para una suscripcion invalidos");
        }
    }
    
    private String[] generateTags(String tagsText){
        Tag tag = new Tag();
        if (tagsText == null) {
            return null;
        }
        return tag.generateTags(tagsText);
    }
}
