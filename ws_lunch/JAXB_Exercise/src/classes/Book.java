package classes;

import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 7/22/13
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 */

@XmlElement
public class Book {
    private String title;
    private String publisher;
    private Author author;
    public Book(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
