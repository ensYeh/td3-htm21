package fr.uvsq.cprog.collex;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {   
        AdresseIP ad = new AdresseIP("192.192.192");
        NomMachine nm = new NomMachine("www.uvsq.fr");
        DnsItem di = new DnsItem(nm, ad);
        System.out.println(di.getDns());
    }
}
