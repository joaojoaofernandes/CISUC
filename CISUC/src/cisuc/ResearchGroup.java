//João Filipe Domingues Fernandes
//2019220273
package cisuc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Research Group Info<br>
 * 
 * groupName - Name of a Group<br>
 * acronym - Acronym of a Group<br>
 * inChargeResearcher - Object Researcher with the info of the Researcher in Charge of a group<br>
 * researchersInGroup - ArrayList with the researchers info of a Group<br>
 */
public class ResearchGroup implements Serializable {

    private String groupName;
    private String acronym;
    private Researcher inChargeResearcher;
    private ArrayList<Researcher> researchersInGroup;

    /**
     * Initializes all the info of a Research Group
     * 
     * @param groupName
     * @param acronym
     * @param inChargeResearcher 
     */
    public ResearchGroup(String groupName, String acronym, Researcher inChargeResearcher) {
        researchersInGroup = new ArrayList<>();
        this.groupName = groupName;
        this.acronym = acronym;
        this.inChargeResearcher = inChargeResearcher;
        researchersInGroup.add(inChargeResearcher);
    }

    /**
     * Adds a researcher to the ArrayList researchersInGroup
     * 
     * @param researcher 
     */
    public void setResearchersInGroup(Researcher researcher) {
        researchersInGroup.add(researcher);
    }

    /**
     * Gets the Acronym of a group
     * 
     * @return acronym
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Gets the ArrayList of the researchers in a group
     * 
     * @return researchersInGroup
     */
    public ArrayList<Researcher> getResearchersInGroup() {
        return researchersInGroup;
    }

        /**
     * Prints the info
     * 
     * @return String
     */
    @Override
    public String toString() {
        String sem = acronym + "\n";
        sem += "Membros efetivos:\n";
        String ss = "\nEstudantes:\n";
        for (Researcher r : researchersInGroup) {
            if (r.isEffectiveMember()) {
                sem += r + "\n";
            } else {
                ss += r + "\n";
            }
        }
        return sem + ss;
    }

}
