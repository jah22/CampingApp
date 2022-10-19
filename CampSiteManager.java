//Copyright @jordansfowler

import java.util.ArrayList;

public class CampSiteManager{
  private String name;
  private String address;
  private float pricePerCamperPerDay;
  private ArrayList<String> frequentlyAskedQuestions;

  private static Campsite campsite;
  private String authcode;

  private CampSiteManager() {
    //To-Do
  }
  private static Campsite getInstance() {
    //To-Do
  }
  public String getName() {
    //To-Do
    return name;
  }
  public String getddress() {
    //To-Do
    return address;
  }
  public void seeCoordinators() {
    //To-Do

  }
  public String toString() {
    //To-Do
    return null;
  }
  public void seeAdmins() {
//To-Do
  }
  public void seeCabins() {
//To-Do
  }
  public void seeAllActivities() {
//To-Do
  }
  public boolean seeAllCabinActivities(String cabinId) {
    //To-Do
    return false;
  }
  public boolean seeCabinCoordinators(String cabinID) {
    //To-Do
    return false;
  }
  public boolean addCamperToCabin(Dependent camper) {
    //To-Do
    return false;
  }
  public boolean removeCamperFromCabin(Dependent camper) {
    //To-Do
    return false;
  }
  public boolean addCoordinatorToCabin(Dependent coordinator) {
    //To-Do
    return false;
  }
  public boolean removeCoordinatorFromCabin(Dependent coordinator) {
    //To-Do
    return false;
  }
  public void seeAvgReviews() {
    //To-Do
  }
  public boolean seeReviewsByGuardian(String firstName, String lastName) {
    //To-Do
    return false;
  }
  public boolean seeReviewsByRating(int rating) {
    //To-Do
    return false;
  }
  public boolean addReview(String firstName, String lastName, int rating, String text) {
    //To-Do
    return false;
  }
  public boolean registerGuardian() {
    //To-Do
    return false;
  }
  public boolean registerDependent() {
    //To-Do
    return false;
  }
  public boolean removeDependent(Dependent dept) {
    //To-Do
    return false;
  }
  public boolean removeGuardian(String firstName, String lastName) {
    //To-Do
    return false;
  }
  public boolean registerAdmin(CampAdmin admin) {
    //To-Do
    return false;
  }
  public boolean registerCoordinator(Dependent coord) {
    //To-Do
    return false;
  }
  public boolean removeCoordinator(Dependent coord) {
    //To-Do
    return false;
  }
  public boolean removeCamper(Dependent camper) {
    //To-Do
    return false;
  }
  public boolean payForCamper(String camperFirstName, String camperLastName) {
    //To-Do
    return false;
  }
  public boolean refundGuardian(String camperFirstName, String capmerLastName) {
    //To-Do
    return false;
  }
  public boolean signUpDependentForCabin(String guardianUserName, String guardianPassword) {
    //To-Do
    return false;
  }
  public boolean viewDependents(String username, String password) {
    //To-Do
    return false;
  }
  public boolean addDependent(String guardianUsername, String guardianPassword) {
    //To-Do
    return false;
  }
  public boolean getDependentCabin(Dependent dept) {
    //To-Do
    return false;
  }
  public boolean showDependentContacts(Dependent dept) {
    //To-Do
    return false;
  }
  public boolean hasPaid(Dependent camper) {
    //To-Do
    return false;
  }
  public boolean writeReview(Guardian author, String text, int rating) {
    //To-Do
    return false;
  }
  public boolean removeReview(Guardian author, String text, int rating) {
    //To-Do
    return false;
  }
  public boolean updateLoginInformation(String curUsername, String curPassword) {
    //To-Do
    return false;
  }
  public boolean getMedicalNotes(String username, String password) {
    //To-Do
    return false;
  }
  public boolean getContactInformation(String authUsername, String authPassword) {
    //To-Do
    return false;
  }
  public boolean login() {
    //To-Do
    return false;
  }
  public boolean logout() {
    //To-Do
    return false;
  }
}