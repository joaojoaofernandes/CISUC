//João Filipe Domingues Fernandes
//2019220273
package cisuc;

import java.io.Serializable;

/**
 * Researcher Info<br>
 * 
 * name - name of a Researcher<br>
 * email - email of a Researcher<br>
 * group - name<br>
 */
public abstract class Researcher implements Serializable {

    private String name;
    private String email;
    private String group;

    /**
     * Initializes all the info of a Researcher
     * 
     * @param name
     * @param email
     * @param group 
     */
    public Researcher(String name, String email, String group) {
        this.name = name;
        this.email = email;
        this.group = group;
    }

    /**
     * Gets the name of a Researcher
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This Method will be overridden in derived class
     *
     * @return false
     */
    public boolean isEffectiveMember() {
        return false;
    }

    /**
     * This Method will be overridden in derived class
     *
     * @return false
     */
    public boolean isStudent() {
        return false;
    }

    /**
     * Prints the info
     * 
     * @return String
     */
    @Override
    public String toString() {
        return name;//+ email + group;
    }

}
