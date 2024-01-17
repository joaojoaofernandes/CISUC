//João Filipe Domingues Fernandes
//2019220273
package cisuc;

/**
 * Conference Article Info<br>
 * 
 * articleName - Name of a Article<br>
 * date - Object Date that contains the date of a Conference Article <br>
 * location - local wjere the Conference occurred<br>
 * 
 */
public class ConferenceArticle extends Publication {
    
    private String articleName;
    private Date date;
    private String location;

    /**
     * Initializes all the info of a Conference Article
     *
     * @param publicationTitle
     * @param keyWords
     * @param yearOfPublication
     * @param audience
     * @param articleName
     * @param date
     * @param location
     */
    public ConferenceArticle(String publicationTitle, String keyWords, int yearOfPublication, int audience, String articleName, Date date, String location) {
        super(publicationTitle, keyWords, yearOfPublication, audience);
        this.articleName = articleName;
        this.date = date;
        this.location = location;
    }

    /**
     * This method overrides calculateImpactFactor() of Parent
     * Calculates the Impact factor of a Conference Article determined by the
     * Conference audience
     *
     * @param audience
     * @return "A","B" or "C"
     */
    @Override
    public String calculateImpactFactor(int audience) {
        if (audience >= 500) {
            return "A";
        } else if (audience >= 200) {
            return "B";
        } else {
            return "C";
        }
    }

    /**
     * This method overrides getType() of Parent
     * Gets the type of A publication
     *
     * @return "C"
     */
    @Override
    public String getType() {
        return "C";
    }

    /**
     * This method overrides isConferenceArticle() of Parent
     *
     * @return true
     */
    @Override
    public boolean isConferenceArticle() {
        return true;
    }

        /**
     * Prints the info
     * 
     * @return String
     */
    public String toString() {
        String ss = super.toString();
        ss += articleName + "/" + date + "/" + location;
        return ss;
    }
}
