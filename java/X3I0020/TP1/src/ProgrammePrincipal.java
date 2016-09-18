public class ProgrammePrincipal{
  public static void main(String[] args) {
    Compte mamadou = new Compte("mamadou",500);
    Compte ousmane = new Compte("ousmane",100);
    mamadou.infoCompte();
    mamadou.crediter(100);
    mamadou.infoCompte();
    ousmane.infoCompte();

  }
}
