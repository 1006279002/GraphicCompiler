


package Main;

import Semantic.Semantic;

public class Main {
  //  public static SemanticImp semanticImp = new SemanticImp(new Draw());


    public static void main(String[] args)
    {
        Semantic semanticImp = new Semantic(new Draw());
        semanticImp.Parser("test.txt");
    }
}
