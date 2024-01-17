//João Filipe Domingues Fernandes
//2019220273
package cisuc;

/**
 * Book Info<br>
 *
 * publishingCompany - Name of the publishing Company bookISBN - ISBN number<br>
 * bookISBN - ISBN number<br>
 * resume - resume of a Book<br>
 */
public class Book extends Publication {

    private String publishingCompany;
    private String bookISBN;
    private String resume;

    /**
     * Initializes all the info of a Book
     * 
     * @param publicationTitle
     * @param keyWords
     * @param yearOfPublication
     * @param audience
     * @param publishingCompany
     * @param bookISBN
     * @param resume 
     */
    public Book(String publicationTitle, String keyWords, int yearOfPublication, int audience, String publishingCompany, String bookISBN, String resume) {
        super(publicationTitle, keyWords, yearOfPublication, audience);
        this.publishingCompany = publishingCompany;
        this.bookISBN = bookISBN;
        this.resume = resume;
    }

    /**
     * This method overrides calculateImpactFactor() of Parent
     * Calculates the Impact factor of a Book determined by the Book audience
     *
     * @param audience
     * @return "A","B" or "C"
     */
    @Override
    public String calculateImpactFactor(int audience) {
        if (audience >= 10000) {
            return "A";
        } else if (audience >= 5000) {
            return "B";
        } else {
            return "C";
        }
    }

     /**
     * This method overrides getType() of Parent
     * Gets the type of A publication
     *
     * @return "B"
     */
    public String getType() {
        return "B";
    }

    /**
     * This method overrides isBook() of Parent
     *
     * @return true
     */
    public boolean isBook() {
        return true;
    }
    
        /**
     * Prints the info
     * 
     * @return String
     */
    public String toString() {
        String ss = super.toString();
        ss += publishingCompany + "/" + bookISBN + "/" + resume+"/";
        return ss;
    }
}
