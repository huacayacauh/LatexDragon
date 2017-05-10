package libreDragon.generateRuleParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Main {

	/**
	 * @param args  args[0] is the path of the input configuration file
	 *              args[1] is the path of the output grammar file
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String line = null;

		BufferedReader buff =  new BufferedReader(new FileReader(args[0]));

		ArrayList<Specification> list = new ArrayList<Specification>();

		String[] splitLine;

		String specification;

		while( (line = buff.readLine() ) != null)
		{
			splitLine = line.split(" ");

			specification = splitLine[0];

			if(specification.equals("Unaire"))
				list.add(new SpecificationUnaire(splitLine[1], splitLine[2], splitLine[3]));

			if(specification.equals("Primaire"))
				list.add(new SpecificationPrimaire(splitLine[1], splitLine[2]));

			if(specification.equals("Binaire"))
				list.add(new SpecificationBinaire(splitLine[1], splitLine[2], Integer.parseInt(splitLine[3])));

		}

		buff.close();

		GenerGrammarRules r = new GenerGrammarRules(list, args[1]);
		r.generateGrammar();
	}

}
