     //João Filipe Domingues Fernandes
//2019220273
package cisuc;

/**
 * Conference Article Book<br>
 * 
 * confereceName - Name of the Conference<br>
 * numberOfArticles - number of articles of a Book<br>
 * 
 */
public class ConferenceArticleBook extends Book {

    private String conferenceName;
    private int numbersOfArticles;

    /**
     * Initializes all the info of a Conference Article Book
     *
     * @param publicationTitle
     * @param keyWords
     * @param yearOfPublication
     * @param audience
     * @param publishingCompany
     * @param bookISBN
     * @param resume
     * @param conferenceName
     * @param numbersOfArticles
     */
    public ConferenceArticleBook(String publicationTitle, String keyWords, int yearOfPublication, int audience, String publishingCompany, String bookISBN, String resume, String conferenceName, int numbersOfArticles) {
        super(publicationTitle, keyWords, yearOfPublication, audience, publishingCompany, bookISBN, resume);
        this.conferenceName = conferenceName;
        this.numbersOfArticles = numbersOfArticles;
    }

    /**
     * This method overrides calculateImpactFactor() of Parent Calculates the
     * Impact factor of a Conference Article Book determined by the Book
     * audience
     *
     * @param audience
     * @return "A","B" or "C"
     */
    @Override
    public String calculateImpactFactor(int audience) {
        if (audience >= 7500) {
            return "A";
        } else if (audience >= 2500) {
            return "B";
        } else {
            return "C";
        }
    }

    /**
     * This method overrides getType() of Parent Gets the type of A publication
     *
     * @return "BCA"
     */
    @Override
    public String getType() {
        return "BCA";
    }

    /**
     * This method overrides isConferenceArticleBook() of Parent
     *
     * @return true
     */
    @Override
    public boolean isConferenceArticleBook() {
        return true;
    }
    
        /**
     * Prints the info
     * 
     * @return String
     */
    @Override
    public String toString() {
        String ss = super.toString();
        ss += conferenceName + "/" + numbersOfArticles;
        return ss;
    }
}
