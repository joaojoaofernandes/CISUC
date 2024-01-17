//João Filipe Domingues Fernandes
//2019220273
package cisuc;

/**
 * Book Chapter Info<br>
 * 
 * chapterName - Name of the chapter of a Book<br>
 * initialPage - Initial page of a Book Chapter<br>
 * endPage - End page of a Book Chapter<br>
 * 
 */
public class BookChapter extends Book {

    private String chapterName;
    private int initialPage;
    private int endPage;

    /**
     * Initializes all the info of a Book Chapter
     * 
     * @param publicationTitle
     * @param keyWords
     * @param yearOfPublication
     * @param audience
     * @param publishingCompany
     * @param bookISBN
     * @param resume
     * @param chapterName
     * @param initialPage
     * @param endPage 
     */
    public BookChapter(String publicationTitle, String keyWords, int yearOfPublication, int audience, String publishingCompany, String bookISBN, String resume, String chapterName, int initialPage, int endPage) {
        super(publicationTitle, keyWords, yearOfPublication, audience, publishingCompany, bookISBN, resume);
        this.chapterName = chapterName;
        this.initialPage = initialPage;
        this.endPage = endPage;
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
     * @return "BC"
     */
    public String getType() {
        return "BC";
    }

        /**
     * This method overrides isBookChapter() of Parent
     *
     * @return true
     */
    public boolean isBookChapter() {
        return true;
    }

        /**
     * Prints the info
     * 
     * @return String
     */
    public String toString() {
        String ss = super.toString();
        ss += chapterName + "/" + initialPage + "/" + endPage;
        return ss;
    }
}
