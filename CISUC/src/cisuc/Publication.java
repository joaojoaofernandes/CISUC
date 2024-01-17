//João Filipe Domingues Fernandes
//2019220273
package cisuc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Publication information<br>
 *
 * researchersOfAPubliction - ArrayList with the researchers info of a publication <br>
* publicationTitle - name of the Publication <br>
 * keyWords - String with the words essentials of a publication <br>
 * yearOfPublication - year when the publication was presented published <br>
 * audience - number of the audience<br>
 * impactFactor - attributed by the number of the audience of a certain type of<br>
 * publication<br>
 *
 */
public abstract class Publication implements Comparable<Publication>, Serializable {

    private ArrayList<Researcher> researchersOfAPublication;
    private String publicationTitle;
    private String keyWords;
    private int yearOfPublication;
    private int audience;
    private String impactFactor;

    /**
     * Initializes all the info of a Publication
     *
     * @param publicationTitle
     * @param keyWords
     * @param yearOfPublication
     * @param audience
     */
    public Publication(String publicationTitle, String keyWords, int yearOfPublication, int audience) {
        researchersOfAPublication = new ArrayList<>();
        this.publicationTitle = publicationTitle;
        this.keyWords = keyWords;
        this.yearOfPublication = yearOfPublication;
        this.audience = audience;
    }

    /**
     * This Method will be overridden in derived class
     *
     * @param audience
     * @return empty String
     */
    public abstract String calculateImpactFactor(int audience);


    /**
     * Sets the impactFactor
     *
     * @param impactFactor
     */
    public void setImpactFactor(String impactFactor) {
        this.impactFactor = impactFactor;
    }

    /**
     * Gets the impactFactor
     *
     * @return impactFactor
     */
    public String getImpactFactor() {
        return impactFactor;
    }

    /**
     * Gets the ArrayList with the Researchers of a Publication
     *
     * @return researchersOfAPublication
     */
    public ArrayList<Researcher> getResearchersOfAPublication() {
        return researchersOfAPublication;
    }

    /**
     * Adds a researcher to the ArrayList researchersOfAPublication
     *
     * @param researcher
     */
    public void setResearchersOfAPublication(Researcher researcher) {
        researchersOfAPublication.add(researcher);
    }

    /**
     * Gets the publication title, year and impactFactor
     *
     * @return
     */
    public String getPublicationTitle() {
        return getType() + " " +publicationTitle + "    " + yearOfPublication + "     " + impactFactor;
    }

    /**
     * Gets the year Of Publication
     *
     * @return yearOfPublication
     */
    public int getYearOfPublication() {
        return yearOfPublication;
    }

    /**
     * This Method will be overridden in derived class
     *
     * @return empty String
     */
     public abstract String getType() ;

    /**
     * This Method will be overridden in derived class
     *
     * @return false
     */
    public boolean isConferenceArticle() {
        return false;
    }

    /**
     * This Method will be overridden in derived class
     *
     * @return false
     */
    public boolean isMagazineArticle() {
        return false;
    }

    /**
     * This Method will be overridden in derived class
     *
     * @return false
     */
    public boolean isBook() {
        return false;
    }

    /**
     * This Method will be overridden in derived class
     *
     * @return false
     */
    public boolean isBookChapter() {
        return false;
    }

    /**
     * This Method will be overridden in derived class
     *
     * @return false
     */
    public boolean isConferenceArticleBook() {
        return false;
    }
    
    /**
     * Compares the elements of a ArrayList
     * 
     * @param p
     * @return compare
     */
    public int compareTo(Publication p) {
        int compare = -Integer.compare(yearOfPublication, p.yearOfPublication);
        if (compare == 0) {
            compare = getType().compareTo(p.getType());
            if (compare == 0) {
                compare = -p.getImpactFactor().compareTo(getImpactFactor());
            }
        }
        return compare;
    }

    /**
     * Prints the info
     * 
     * @return String
     */
    @Override
    public String toString() {
        String ss = getType() + "/" + publicationTitle + "/"+keyWords+"/" + yearOfPublication + "/"+ audience+"/"+ impactFactor + "/";
        return ss;
    }
}
