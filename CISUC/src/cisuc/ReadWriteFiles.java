//João Filipe Domingues Fernandes
//2019220273
package cisuc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * In this class is where all the data will be read and loaded
 *
 */
public class ReadWriteFiles {

    /**
     * Constructor Empty
     *
     */
    public ReadWriteFiles() {

    }

    /**
     * Reads the text file for Researchers The researchers after the line
     * "Effective Members:" are effective members, and the researchers after the
     * line "Students:" are students than procedes to create the Object
     * Corresponding to the Researcher
     *
     * @param file
     * @param cisuc
     */
    public void readResearchersFile(String file, Cisuc cisuc) {
        File f = new File(file);
        if (f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                boolean researcherIsStudent = false; //Bollean to compare if 
                int numOfLines = 0;
                while ((line = br.readLine()) != null) {
                    if (line.equals("Effective Members:")) {
                        researcherIsStudent = false;
                        numOfLines++;
                        line = br.readLine();
                    } else if (line.equals("Students:")) {
                        researcherIsStudent = true;
                        numOfLines++;
                        line = br.readLine();
                    }
                    numOfLines++;
                    String[] researchersInfo = line.split("/");
                    try {
                        if (!researcherIsStudent) {
                            Researcher researcher = new EffectiveMember(researchersInfo[0], researchersInfo[1], researchersInfo[2], Integer.parseInt(researchersInfo[3]), Integer.parseInt(researchersInfo[4]));
                            cisuc.setResearchers(researcher);
                        } else {
                            Researcher guidingResearcher = cisuc.findResearcher(researchersInfo[7]);
                            Date dateOfDelivery = new Date(researchersInfo[4], researchersInfo[5], researchersInfo[6]);
                            Researcher researcher = new Student(researchersInfo[0], researchersInfo[1], researchersInfo[2], researchersInfo[3], dateOfDelivery, guidingResearcher);
                            cisuc.setResearchers(researcher);
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Erro na linha " + numOfLines + " do ficheiro " + file);
                        System.exit(0);
                    }
                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro nao existe.");
        }
    }

    /**
     * Reads the text file for Research Groups If one line contais information
     * of a group than it creates a Object Research Group if not than it adds a
     * Researcher to the last Research Group added
     *
     * @param file
     * @param cisuc
     */
    public void readResearchGroupsFile(String file, Cisuc cisuc) {
        File f = new File(file);
        ResearchGroup rg = null;
        if (f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] researchGroupInfo = line.split("/");
                    if (isResearchGroupInfo(researchGroupInfo.length)) {
                        Researcher inChargeResearcher = cisuc.findResearcher(researchGroupInfo[2]);
                        if (!isInChargeResearcherEffectiveMember(inChargeResearcher)) {
                            System.out.println("Investigador " + inChargeResearcher.getName() + " não tem o cargo de membro efetivo");
                            System.exit(0);
                        }
                        ResearchGroup researchGroup = new ResearchGroup(researchGroupInfo[0], researchGroupInfo[1], inChargeResearcher);
                        rg = researchGroup;
                        cisuc.setResearchGroups(researchGroup);
                    } else {
                        Researcher researcher = cisuc.findResearcher(researchGroupInfo[0]);
                        rg.setResearchersInGroup(researcher);
                    }
                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro nao existe.");
        }
    }

    /**
     * Reads the text file for publications One line contains the information
     * about the Article type, the next line the Article information
     *
     * @param file
     * @param cisuc
     */
    public void readPublicationsFile(String file, Cisuc cisuc) {
        File f = new File(file);
        int numOfLines = 0;
        if (f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    numOfLines++;
                    if (line.equals("Conference Article:")) {
                        numOfLines++;
                        conferenceArticleInfo(cisuc, br.readLine().split("/"), numOfLines);
                    } else if (line.equalsIgnoreCase("Magazine Article:")) {
                        numOfLines++;
                        magazineArticleInfo(cisuc, br.readLine().split("/"), numOfLines);
                    } else if (line.equalsIgnoreCase("Book:")) {
                        numOfLines++;
                        bookInfo(cisuc, br.readLine().split("/"), numOfLines);
                    } else if (line.equalsIgnoreCase("Book Chapter:")) {
                        numOfLines++;
                        bookChapterInfo(cisuc, br.readLine().split("/"), numOfLines);
                    } else if (line.equalsIgnoreCase("Conference Article Book:")) {
                        numOfLines++;
                        conferenceArticleBookInfo(cisuc, br.readLine().split("/"), numOfLines);
                    } else {
                        System.out.println("Erro na linha " + numOfLines);
                    }
                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro nao existe.");
        }
    }

    /**
     * Reads the data from the object file
     * 
     * @param file
     * @param cisuc
     * @return true or false
     */
    public boolean readObjFile(String file, Cisuc cisuc) {
        File f = new File(file);
        ArrayList<Researcher> researchers = new ArrayList<>();
        ArrayList<ResearchGroup> researchGroups = new ArrayList<>();
        ArrayList<Publication> publications = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            researchers = (ArrayList) ois.readObject();
            researchGroups = (ArrayList) ois.readObject();
            publications = (ArrayList) ois.readObject();
            cisuc.setArraylistResearchers(researchers);
            cisuc.setArraylistResearchGroups(researchGroups);
            cisuc.setArraylistPublications(publications);
            ois.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro Objeto.");
        } catch (IOException ex) {
            System.out.println("Erro a ler ficheiro.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
        return false;
    }

    /**
     * Writes the ArrayLists in a Object File
     *
     * @param file
     * @param cisuc
     */
    public void writeObjFile(String file, Cisuc cisuc) {
        File f = new File(file);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cisuc.getResearchers());
            oos.writeObject(cisuc.getResearchGroups());
            oos.writeObject(cisuc.getPublications());
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    /**
     * Sets all information about a Conference Article
     *
     * @param cisuc
     * @param line
     * @param numOfLines
     */
    private void conferenceArticleInfo(Cisuc cisuc, String[] line, int numOfLines) {
        try {
            Date date = new Date(line[6], line[7], line[8]);
            Publication publication = new ConferenceArticle(line[0], line[1], Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], date, line[9]);
            String impactValue = publication.calculateImpactFactor(Integer.parseInt(line[4]));
            publication.setImpactFactor(impactValue);
            cisuc.setPublications(publication);
            addResearchersToAPublication(line[2].split(","), publication, cisuc);
        } catch (NumberFormatException ex) {
            System.out.println("Erro a ler a linha " + numOfLines + " do ficheiro publicações");
            System.exit(0);
        }
    }

    /**
     * Sets all information about a Magazine Article
     *
     * @param cisuc
     * @param line
     * @param numOfLines
     */
    private void magazineArticleInfo(Cisuc cisuc, String[] line, int numOfLines) {
        try {
            Date date = new Date(line[6], line[7], line[8]);
            Publication publication = new MagazineArticle(line[0], line[1], Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], date, Integer.parseInt(line[9]), line[10]);
            String impactValue = publication.calculateImpactFactor(Integer.parseInt(line[4]));
            publication.setImpactFactor(impactValue);
            cisuc.setPublications(publication);
            addResearchersToAPublication(line[2].split(","), publication, cisuc);
        } catch (NumberFormatException ex) {
            System.out.println("Erro a ler a linha " + numOfLines + " do ficheiro publicações");
            System.exit(0);
        }
    }

    /**
     * Sets all information about a Book
     *
     * @param cisuc
     * @param line
     * @param numOfLines
     */
    private void bookInfo(Cisuc cisuc, String[] line, int numOfLines) {
        try {
            Publication publication = new Book(line[0], line[1], Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], line[6], line[7]);
            String impactValue = publication.calculateImpactFactor(Integer.parseInt(line[4]));
            publication.setImpactFactor(impactValue);
            cisuc.setPublications(publication);
            addResearchersToAPublication(line[2].split(","), publication, cisuc);
        } catch (NumberFormatException ex) {
            System.out.println("Erro a ler a linha " + numOfLines + " do ficheiro publicações");
            System.exit(0);
        }
    }

    /**
     * Sets all information about a Book Chapter
     *
     * @param cisuc
     * @param line
     * @param numOfLines
     */
    private void bookChapterInfo(Cisuc cisuc, String[] line, int numOfLines) {
        try {
            Publication publication = new BookChapter(line[0], line[1], Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], line[6], line[7], line[8], Integer.parseInt(line[9]), Integer.parseInt(line[10]));
            String impactValue = publication.calculateImpactFactor(Integer.parseInt(line[4]));
            publication.setImpactFactor(impactValue);
            cisuc.setPublications(publication);
            addResearchersToAPublication(line[2].split(","), publication, cisuc);
        } catch (NumberFormatException ex) {
            System.out.println("Erro a ler a linha " + numOfLines + " do ficheiro publicações");
            System.exit(0);
        }
    }

    /**
     * Sets all information about a Conference Article Book
     *
     * @param cisuc
     * @param line
     * @param numOfLines
     */
    private void conferenceArticleBookInfo(Cisuc cisuc, String[] line, int numOfLines) {
        try {
            Publication publication = new ConferenceArticleBook(line[0], line[1], Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], line[6], line[7], line[8], Integer.parseInt(line[9]));
            String impactValue = publication.calculateImpactFactor(Integer.parseInt(line[4]));
            publication.setImpactFactor(impactValue);
            cisuc.setPublications(publication);
            addResearchersToAPublication(line[2].split(","), publication, cisuc);
        } catch (NumberFormatException ex) {
            System.out.println("Erro a ler a linha " + numOfLines + " do ficheiro publicações");
            System.exit(0);
        }
    }

    /**
     * Adds a Researcher to the Arraylist researchersOfAPublication in a Object
     * Publication
     *
     * @param researchers
     * @param publication
     * @param cisuc
     */
    public void addResearchersToAPublication(String[] researchers, Publication publication, Cisuc cisuc) {
        for (int i = 0; i < researchers.length; i++) {
            Researcher researcher = cisuc.findResearcher(researchers[i]);
            publication.setResearchersOfAPublication(researcher);
        }
    }

    /**
     * Checks if a inChargeResearcher is a Effective Member or not
     *
     * @param inChargeResearcher
     * @return true, false
     */
    private boolean isInChargeResearcherEffectiveMember(Researcher inChargeResearcher) {
        if (inChargeResearcher.isEffectiveMember()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a line is for ResearchGroup information or not
     *
     * @param infoLength
     * @return
     */
    public boolean isResearchGroupInfo(int infoLength) {
        if (infoLength == 3) {
            return true;
        } else {
            return false;
        }
    }
}
