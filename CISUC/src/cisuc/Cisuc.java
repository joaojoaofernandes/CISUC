//João Filipe Domingues Fernandes
//2019220273
package cisuc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Main Class,in this class are all the methods necessary to produce the<br>
 * listings and the indicators<br>
 *
 *
 * researchers - ArrayList of the type Researcher with all the Researchers info<br>
 * researchGroups - ArrayList of the type ResearchGroup with all the Research<br>
 * Groups info publications - ArrayList of the type Publication with all the<br>
 * publications info<br>
 */
public class Cisuc {

    private ArrayList<Researcher> researchers;
    private ArrayList<ResearchGroup> researchGroups;
    private ArrayList<Publication> publications;

    /**
     * Initializes the ArrayLists
     *
     */
    public Cisuc() {
        researchers = new ArrayList<>();
        researchGroups = new ArrayList<>();
        publications = new ArrayList<>();
    }

    /**
     * Main method where the MENU is displayed It also checks from what files
     * the data will be read
     *
     * @param args
     */
    public static void main(String[] args) {
        Cisuc cisuc = new Cisuc();
        ReadWriteFiles rwf = new ReadWriteFiles();
        if (rwf.readObjFile("Cisuc.obj", cisuc)) {   // Checks if a Object file exists, if it exists reads the info from the object file
            rwf.readObjFile("Cisuc.obj", cisuc);         //if it doesnt exixts then reads the info from the text files
        } else {
            rwf.readResearchersFile("Researchers.txt", cisuc);
            rwf.readResearchGroupsFile("ResearchGroups.txt", cisuc);
            rwf.readPublicationsFile("Publications.txt", cisuc);
        }
        System.out.println("----- MENU -----");
        int choice;
        String acronym;
        Scanner input = new Scanner(System.in);
        Scanner stdin = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("1 - Apresentar os indicadores gerais do CISUC:\n" + "    a) Total de membros\n" + "    b) Número de membros de cada categoria\n" + "    c) Total de publicações dos últimos 5 anos\n" + "    d) Número de publicações de cada tipo ");
            System.out.println("2 - Listar as publicações de um grupo de investigação, dos últimos 5 anos, organizadas por ano, por tipo de publicação e por fator de impacto");
            System.out.println("3 - Listar os membros de um grupo de investigação agrupados por categoria");
            System.out.println("4 - Listar as publicações de um investigador agrupadas por ano, tipo de publicação e fator de impacto");
            System.out.println("5 - Listar todos os grupos de investigação, e apresentar para cada grupo:\n" + "    a) Total de membros\n" + "    b) Número de membros de cada categoria\n" + "    c) Total de publicações dos últimos 5 anos\n" + "    d) Número de publicações, dos últimos 5 anos, agrupadas por ano, tipo de publicação e fator de impacto");
            System.out.println("0 - Sair");
            System.out.print("Escolha:");
            choice = stdin.nextInt();
            switch (choice) {
                case 1:
                    cisuc.printNumberOfResearchers(cisuc.getResearchers());
                    System.out.println("");
                    cisuc.numberOfmembersOfAType(cisuc.getResearchers());
                    System.out.println("");
                    cisuc.printPublicationsof5YearsAgo();
                    System.out.println("");
                    cisuc.printHowManyPublicationsOfAType(cisuc.getResearchers(), cisuc.getPublications());
                    break;
                case 2:
                    System.out.println("");
                    cisuc.printResearchGroupAcronyms();
                    System.out.print("Acrónimo do Grupo de Investigação: ");
                    acronym = input.next();
                    cisuc.printPublicationsOfAResearchGroup(acronym);
                    break;
                case 3:
                    System.out.println("");
                    cisuc.printResearchGroupAcronyms();
                    System.out.print("Acrónimo do Grupo de Investigação: ");
                    acronym = input.next();
                    cisuc.printResearchGroup(acronym);
                    break;
                case 4:
                    System.out.println("");
                    cisuc.printResearchers(cisuc.getResearchers());
                    System.out.print("Nome do Investigador: ");
                    String nameOfResearcher = input.nextLine();
                    cisuc.printPublicationsOfAResearcher(nameOfResearcher);
                    break;
                case 5:
                    System.out.println("");
                    cisuc.reseachGroupsInfo();
                    break;
                case 0:
                    rwf.writeObjFile("Cisuc.obj", cisuc);
                    System.exit(0);
            }
        } while (choice != 0);
        stdin.close();
    }

    //--------------------------------------- 1 ----------------------------------------------------
    /**
     * Prints the number of Researchers that exists in the ArrayList given
     *
     * @param researchers
     */
    private void printNumberOfResearchers(ArrayList<Researcher> researchers) {
        System.out.print("Número de Investigadores:" + researchers.size());
    }

    /**
     * Prints the number of diferent types of researchers that exists in the
     * ArrayList given
     *
     * @param researchers
     */
    private void numberOfmembersOfAType(ArrayList<Researcher> researchers) {
        int numberOfStudents = 0;
        int numberOfEffectiveMembers = 0;
        for (Researcher r : researchers) {
            if (r.isStudent()) {
                numberOfStudents++;
            } else {
                numberOfEffectiveMembers++;
            }
        }
        System.out.println("Membros Efetivos:" + numberOfEffectiveMembers + " \nMembros Estudantes:" + numberOfStudents);
    }

    /**
     * Prints the publications of 5 years ago
     *
     */
    private void printPublicationsof5YearsAgo() {
        System.out.print("Publicações:");
        int num = 0;
        for (Publication p : publications) {
            if (p.getYearOfPublication() > 2014) {
                num++;
            }
        }
        System.out.println(num);
    }

    /**
     * Prints the numbers of differents types of Articles that CISUC has
     * published
     *
     */
    private void printHowManyPublicationsOfAType(ArrayList<Researcher> r, ArrayList<Publication> publications) {
        System.out.println("Número de publicações de cada tipo:");
        int numberOfCA = 0;
        int numberOfMA = 0;
        int numberOfB = 0;
        int numberOfBC = 0;
        int numberOfCAB = 0;
        ArrayList<Publication> publicationsOfAResearchGroup = new ArrayList<>();
        for (Researcher researcher : r) {
            for (Publication p : publications) {
                if (p.getResearchersOfAPublication().contains(researcher)) {
                    if (!publicationsOfAResearchGroup.contains(p)) {
                        publicationsOfAResearchGroup.add(p);
                    }
                }
            }
        }
        for (Publication pub : publicationsOfAResearchGroup) {
            if (pub.isConferenceArticle()) {
                numberOfCA++;
            } else if (pub.isMagazineArticle()) {
                numberOfMA++;
            } else if (pub.isBookChapter()) {
                numberOfBC++;
            } else if (pub.isConferenceArticleBook()) {
                numberOfCAB++;
            } else if (pub.isBook()) {
                numberOfB++;
            }
        }
        System.out.println("Conference Article:" + numberOfCA + " \nMagazine Article:" + numberOfMA + "\nBook:" + numberOfB + "\nBook Chapter:" + numberOfBC + "\nConference Article Book:" + numberOfCAB);
    }
    //----------------------------------------------------------------------------------------
    //-------------------------------- 2 -----------------------------------------------------
    /**
     * Prints all the Acronyms of the existing Groups of Researchers so the User
     * can choose
     *
     */
    private void printResearchGroupAcronyms() {
        for (ResearchGroup rg : researchGroups) {
            System.out.println(rg.getAcronym());
        }
    }

    /**
     * Search for a acronym of a Research Group
     *
     * @param acronym
     * @return researchGroup - Object of the type ResearchGroup
     */
    private ResearchGroup findResearchGroup(String acronym) {
        ResearchGroup researchGroup = null;
        for (ResearchGroup rg : researchGroups) {
            if (acronym.equalsIgnoreCase(rg.getAcronym())) {
                researchGroup = rg;
            }
        }
        return researchGroup;
    }
    
    /**
     * Prints the publications of a Research Group organize by year, type and
     * Impact Factor
     *
     * @param acronym
     */
    private void printPublicationsOfAResearchGroup(String acronym) {
        System.out.println("");
        ArrayList<Publication> publicationsOfAResearchGroup = new ArrayList<>();
        ResearchGroup rg = findResearchGroup(acronym);
        if (rg == null) {
            System.out.println("Grupo de investigadores " + acronym + " nao existe");
        } else {
            for (Researcher researcher : rg.getResearchersInGroup()) {
                for (Publication p : publications) {
                    if (p.getResearchersOfAPublication().contains(researcher)) {
                        if (!publicationsOfAResearchGroup.contains(p) && p.getYearOfPublication() > 2014) {
                            publicationsOfAResearchGroup.add(p);
                        }
                    }
                }
            }
            if (publicationsOfAResearchGroup.size() == 0) {
                System.out.println("Nenhuma publicação disponivel");
            } else {
                System.out.println("Número de publicações nos ultimos 5 anos: " + publicationsOfAResearchGroup.size());
                System.out.println("Publicações:");
                Collections.sort(publicationsOfAResearchGroup);
                for (Publication p : publicationsOfAResearchGroup) {
                    System.out.println(p.getPublicationTitle());                                //MUDAR PARA P.GETTITLE().
                }
            }
        }
    }
    //---------------------------------------------------------------------------------------
    //------------------------------------------ 3 ------------------------------------------
    /**
     * Prints the names of the researchers in a Research Group seperate by their
     * types
     *
     * @param acronym
     */
    private void printResearchGroup(String acronym) {
        System.out.println("");
        ResearchGroup rg = findResearchGroup(acronym);
        if (rg == null) {
            System.out.println("Grupo de investigadores " + acronym + " nao existe");
        } else {
            System.out.println(rg);
        }
    }

    //--------------------------------------------------------------------------------------
    //----------------------------------------- 4 ------------------------------------------
    /**
     * prints Researchers
     * 
     * @param researchers 
     */
    private void printResearchers(ArrayList<Researcher> researchers) {
        for (Researcher r : researchers) {
            System.out.println(r);
        }
    }

    /**
     * Search for a name of a Researcher
     *
     * @param name
     * @return researcher - Object of the type Researcher
     */
    public Researcher findResearcher(String name) {
        Researcher researcher = null;
        for (Researcher r : researchers) {
            if (name.equalsIgnoreCase(r.getName())) {
                researcher = r;
            }
        }
        if (researcher == null) {
            System.out.println("Investigador " + name + " nao existe");
        }
        return researcher;
    }

    /**
     * Prints the publications of a Researcher organize by year, type and Impact
     * Factor
     *
     * @param nameOfResearcher
     */
    private void printPublicationsOfAResearcher(String nameOfResearcher) {
        ArrayList<Publication> publicationsOfAResearcher = new ArrayList<>();
        Researcher researcher = findResearcher(nameOfResearcher);
        for (Publication p : publications) {
            if (p.getResearchersOfAPublication().contains(researcher)) {
                if (!publicationsOfAResearcher.contains(p)) {
                    publicationsOfAResearcher.add(p);
                }
            }
        }
        Collections.sort(publicationsOfAResearcher);
        if (publicationsOfAResearcher.size() == 0) {
            System.out.println("Nao existem Publicações deste investigador");
        } else {
            for (Publication p : publicationsOfAResearcher) {
                System.out.println(p.getPublicationTitle());
            }
        }
    }

    //--------------------------------------------------------------------------------------
    //----------------------------------- 5 ------------------------------------------------
    /**
     * Calls the functions used in other objectives reciclying them to print the
     * number of Researchers of a Group, number of researchers of differents
     * types and the publications of a Research Group
     *
     */
    private void reseachGroupsInfo() {
        for (ResearchGroup rg : researchGroups) {
            System.out.println("");
            System.out.println("-------------------------------------------");
            System.out.println(rg.getAcronym());
            printNumberOfResearchers(rg.getResearchersInGroup());
            System.out.println("");
            numberOfmembersOfAType(rg.getResearchersInGroup());
            System.out.println("");
            printPublicationsGrouped(rg.getAcronym());
        }
    }

    /**
     * Saves the publications from 5 years ago and prints the certain number for
     * type, year and impact Factor
     *
     * @param acronym
     */
    private void printPublicationsGrouped(String acronym) {
        ArrayList<Publication> pub = new ArrayList<>();
        ResearchGroup rg = findResearchGroup(acronym);
        for (Researcher researcher : rg.getResearchersInGroup()) {
            for (Publication p : publications) {
                if (p.getResearchersOfAPublication().contains(researcher) && p.getYearOfPublication() > 2014) {
                    if (!pub.contains(p)) {
                        pub.add(p);
                    }
                }
            }
        }
        System.out.println("Numero de Publicações nos ultimos 5 anos:"+ pub.size());
        printPublicationsOfAYear(pub);
        System.out.println("");
        printHowManyPublicationsOfAType(rg.getResearchersInGroup(), pub);
        System.out.println("");
        printPublicationsImpactFactor(pub);
    }

    /**
     * Prints the number of publications from a certain year
     *
     * @param pub
     */
    private void printPublicationsOfAYear(ArrayList<Publication> pub) {
        System.out.println("Anos das Publicações");
        int[] years;
        years = new int[6];
        for (Publication p : pub) {
            if (p.getYearOfPublication() == 2015) {
                years[0] += 1;
            } else if (p.getYearOfPublication() == 2016) {
                years[1] += 1;
            } else if (p.getYearOfPublication() == 2017) {
                years[2] += 1;
            } else if (p.getYearOfPublication() == 2018) {
                years[3] += 1;
            } else if (p.getYearOfPublication() == 2019) {
                years[4] += 1;
            } else if (p.getYearOfPublication() == 2020) {
                years[5] += 1;
            }
        }
        System.out.println("2015: " + years[0] + "\n" + "2016: " + years[1] + "\n" + "2017: " + years[2] + "\n" + "2018: " + years[3] + "\n" + "2019: " + years[4] + "\n" + "2020: " + years[5]);
    }

    /**
     * Prints the number of Publications with certain Impact Factior
     *
     * @param pub
     */
    private void printPublicationsImpactFactor(ArrayList<Publication> pub) {
        System.out.println("Fator de Impacto das Publicações");
        int[] years;
        years = new int[3];
        for (Publication p : pub) {
            if (p.getImpactFactor().equals("A")) {
                years[0] += 1;
            } else if (p.getImpactFactor().equals("B")) {
                years[1] += 1;
            } else if (p.getImpactFactor().equals("C")) {
                years[2] += 1;
            }
        }
        System.out.println("A: " + years[0] + "\n" + "B: " + years[1] + "\n" + "C: " + years[2]);
    }

    /**
     * Gets the ArrayList researchers
     *
     * @return researchers
     */
    public ArrayList<Researcher> getResearchers() {
        return researchers;
    }

    /**
     * Gets the ArrayList researchGroups
     *
     * @return researchGroups
     */
    public ArrayList<ResearchGroup> getResearchGroups() {
        return researchGroups;
    }

    /**
     * Gets the ArrayList publications
     *
     * @return publications
     */
    public ArrayList<Publication> getPublications() {
        return publications;
    }

    /**
     * Adds a researcher to the ArrayList researchers
     *
     * @param researcher
     */
    public void setResearchers(Researcher researcher) {
        this.researchers.add(researcher);
    }

    /**
     * Adds a researchGroup to the ArrayList researchGroups
     *
     * @param researchGroup
     */
    public void setResearchGroups(ResearchGroup researchGroup) {
        this.researchGroups.add(researchGroup);
    }

    /**
     * Adds a publication to the ArrayList publications
     *
     * @param publication
     */
    public void setPublications(Publication publication) {
        this.publications.add(publication);
    }

    /**
     * Sets the ArrayList researchers
     *
     * @param researchers
     */
    public void setArraylistResearchers(ArrayList<Researcher> researchers) {
        this.researchers = researchers;
    }

    /**
     * Sets the ArrayList researchGroups
     *
     * @param researchGroups
     */
    public void setArraylistResearchGroups(ArrayList<ResearchGroup> researchGroups) {
        this.researchGroups = researchGroups;
    }

    /**
     * Sets the ArrayList publications
     *
     * @param publications
     */
    public void setArraylistPublications(ArrayList<Publication> publications) {
        this.publications = publications;
    }
}
