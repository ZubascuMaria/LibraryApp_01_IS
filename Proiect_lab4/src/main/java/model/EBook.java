package model;

import java.time.LocalDate;

public class EBook {
    private Long id;
    private String author;
    private String title;
    private LocalDate publishedDate;
    private String format;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getFormat() {
        return format;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    @Override
    public String toString() {
        return "EBook{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", format='" + format + '\'' +
                ", publishedDate=" + publishedDate +
                '}';
    }
}
