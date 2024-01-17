//João Filipe Domingues Fernandes
//2019220273
package cisuc;

/**
 * Magazine Article info<br>
 *
 * magazineName - name of a Magazine<br> 
 * date - Object Date that contains the date of a Magazine Article <br>
 * magazineNumber - number of a Magazine <br>
 * resume - resume<br>
 * of a Magazine Article<br>
 *
 */
public class MagazineArticle extends Publication {
    
    private String magazineName;
    private Date date;
    private int magazineNumber;
    private String resume;

    /**
     * Initializes all the info of a Magazine
     *
     * @param publicationTitle
     * @param keyWords
     * @param yearOfPublication
     * @param audience
     * @param magazineName
     * @param date
     * @param magazineNumber
     * @param resume
     */
    public MagazineArticle(String publicationTitle, String keyWords, int yearOfPublication, int audience, String magazineName, Date date, int magazineNumber, String resume) {
        super(publicationTitle, keyWords, yearOfPublication, audience);
        this.magazineName = magazineName;
        this.date = date;
        this.magazineNumber = magazineNumber;
        this.resume = resume;
    }

    /**
     * This method overrides calculateImpactFactor() of Parent
     * Calculates the Impact factor of a Magazine Article determined by the
     * Magazine audience
     *
     * @param audience
     * @return "A","B" or "C"
     */
    @Override
    public String calculateImpactFactor(int audience) {
        if (audience >= 1000) {
            return "A";
        } else if (audience >= 500) {
            return "B";
        } else {
            return "C";
        }
    }

    /**
     * This method overrides getType() of Parent
     * Gets the type of A publication
     *
     * @return "M"
     */
    public String getType() {
        return "M";
    }

    /**
     * This method overrides isMagazineArticle() of Parent
     *
     * @return true
     */
    @Override
    public boolean isMagazineArticle() {
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
        ss += magazineName + "/" + date + "/"+magazineNumber;
        return ss;
    }
    
}
