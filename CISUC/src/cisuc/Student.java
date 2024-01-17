//João Filipe Domingues Fernandes
//2019220273
package cisuc;

/**
 * Student Info<br>
 *
 * thesisTitle - Title of the thesis <br>
 * dateOfDelivery - date of the delivery of the thesis <br>
 * guidingResearcher - Researcher responsible for the student<br>
 */
public class Student extends Researcher {

    private String thesisTitle;
    private Date dateOfDelivery;
    private Researcher guidingResearcher;

    /**
     * Initializes all the info of a Student
     *
     * @param name
     * @param email
     * @param group
     * @param thesisTitle
     * @param dateOfDelivery
     * @param guidingResearcher
     */
    public Student(String name, String email, String group, String thesisTitle, Date dateOfDelivery, Researcher guidingResearcher) {
        super(name, email, group);
        this.thesisTitle = thesisTitle;
        this.dateOfDelivery = dateOfDelivery;
        this.guidingResearcher = guidingResearcher;
    }

    /**
     * Gets Thesis title
     * 
     * @return thesisTitle
     */
    public String getThesisTitle() {
        return thesisTitle;
    }

    /**
     * Gets date of delivery
     * 
     * @return dateOfDelivery
     */
    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    /**
     * Gets guiding Researcher
     * 
     * @return guidingResearcher
     */
    public Researcher getGuidingResearcher() {
        return guidingResearcher;
    }

    /**
     * This method overrides isEffectiveMember() of Parent
     *
     * @return true
     */
    @Override
    public boolean isStudent() {
        return true;
    }

    @Override
    public String toString() {
        return super.getName();
    }

}
