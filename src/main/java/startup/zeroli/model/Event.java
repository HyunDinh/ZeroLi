package startup.zeroli.model;

import java.util.List;

public class Event {
    private String imageUrl;
    private String eventName;
    private String description;
    private List<String> googleMapsLinks;



    public Event(String imageUrl, String eventName, String description) {
        this.imageUrl = imageUrl;
        this.eventName = eventName;
        this.description = description;
    }

    public Event(String imageUrl, String eventName, String description, List<String> googleMapsLinks) {
        this.imageUrl = imageUrl;
        this.eventName = eventName;
        this.description = description;
        this.googleMapsLinks = googleMapsLinks;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGoogleMapsLinks() {
        return googleMapsLinks;
    }

    public void setGoogleMapsLinks(List<String> googleMapsLinks) {
        this.googleMapsLinks = googleMapsLinks;
    }


}
