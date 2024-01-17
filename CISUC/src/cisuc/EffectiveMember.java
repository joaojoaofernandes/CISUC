//João Filipe Domingues Fernandes
//2019220273
package cisuc;

/**
 * Effective Member Info<br>
 * 
 * cabinetNumber - Number of the cabinet<br>
 * deiPhone - Phone number<br>
 * 
 */
public class EffectiveMember extends Researcher{
    private int cabinetNumber;
    private int deiPhone;

    /**
     * Initializes all the info of a Effective Member
     * 
     * @param name
     * @param email
     * @param group
     * @param cabinetNumber
     * @param deiPhone 
     */
    public EffectiveMember(String name, String email, String group, int cabinetNumber, int deiPhone) {
        super(name, email, group);
        this.cabinetNumber = cabinetNumber;
        this.deiPhone = deiPhone;
    }

    /**
     * Gets the CabinetNumber
     * @return cabinetNumber
     */
    public int getCabinetNumber() {
        return cabinetNumber;
    }

    /**
     * Gets the Phone number
     * @return deiPhone
     */
    public int getDeiPhone() {
        return deiPhone;
    }
    
    /**
     * This method overrides isEffectiveMember() of Parent
     *
     * @return true
     */
    @Override
    public boolean isEffectiveMember(){
        return true;
    }
    
}
