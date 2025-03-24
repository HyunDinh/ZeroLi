package startup.zeroli.model;

public class Canvas {
    private int id;
    private String pageLink;
    private String link;
    private String title;
    private String desc;
    private String categories;
    private String author;
    private int likes; // Thêm thuộc tính likes

    // Constructor đầy đủ
    public Canvas(int id, String pageLink, String link, String title, String desc, String categories, String author, int likes) {
        this.id = id;
        this.pageLink = pageLink;
        this.link = link;
        this.title = title;
        this.desc = desc;
        this.categories = categories;
        this.author = author;
        this.likes = likes;
    }

    // Constructor cho getCanvasImage
    public Canvas(String link) {
        this.link = link;
    }

    public Canvas(int id, String pageLink, String link, String title, String desc, String categories, String author) {
        this.id = id;
        this.pageLink = pageLink;
        this.link = link;
        this.title = title;
        this.desc = desc;
        this.categories = categories;
        this.author = author;
    }

    // Getters và Setters
    public int getId() { return id; }
    public String getPageLink() { return pageLink; }
    public String getLink() { return link; }
    public String getTitle() { return title; }
    public String getDesc() { return desc; }
    public String getCategories() { return categories; }
    public String getAuthor() { return author; }
    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }
}