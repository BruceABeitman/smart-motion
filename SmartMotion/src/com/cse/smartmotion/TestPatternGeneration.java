//
//import java.util.Arrays;
//import java.util.List;
//
//public class Main {
//
//	public static void main(String[] args) {
//		float[] time = {(float)0.,(float)0.039,(float)0.102,(float)0.17,(float)0.23,(float)0.293,(float)0.359,(float)0.421,(float)0.485,(float)0.548,(float)0.611,(float)0.675,(float)0.739,(float)0.802,(float)0.866,(float)0.93,(float)0.998,(float)1.055,(float)1.119,(float)1.183,(float)1.247,(float)1.31,(float)1.377,(float)1.438,(float)1.502,(float)1.566,(float)1.628,(float)1.691,(float)1.755,(float)1.819,(float)1.911,(float)1.946,(float)2.01,(float)2.072,(float)2.136,(float)2.201,(float)2.266,(float)2.328,(float)2.394,(float)2.456,(float)2.518,(float)2.581,(float)2.645,(float)2.709,(float)2.772,(float)2.835,(float)2.9,(float)2.965,(float)3.029,(float)3.091,(float)3.158,(float)3.217,(float)3.28,(float)3.35,(float)3.409,(float)3.472,(float)3.538,(float)3.617,(float)3.673,(float)3.726,(float)3.789,(float)3.852,(float)3.916,(float)3.981,(float)4.044,(float)4.107,(float)4.171,(float)4.24,(float)4.299,(float)4.362,(float)4.428,(float)4.489,(float)4.557,(float)4.616,(float)4.679,(float)4.743,(float)4.809,(float)4.871,(float)4.937,(float)4.998,(float)5.063,(float)5.124,(float)5.187,(float)5.251,(float)5.316,(float)5.379,(float)5.444,(float)5.506,(float)5.577,(float)5.632,(float)5.696,(float)5.76,(float)5.826,(float)5.888,(float)5.95,(float)6.015,(float)6.077,(float)6.145,(float)6.206,(float)6.269,(float)6.338,(float)6.395,(float)6.459,(float)6.523,(float)6.59,(float)6.649,(float)6.713,(float)6.777,(float)6.841,(float)6.908,(float)6.998,(float)7.032,(float)7.098,(float)7.159,(float)7.221,(float)7.285,(float)7.349,(float)7.413,(float)7.477,(float)7.54,(float)7.611,(float)7.666,(float)7.73,(float)7.8,(float)7.861,(float)7.92,(float)7.985,(float)8.049,(float)8.138,(float)8.176,(float)8.249,(float)8.302,(float)8.366,(float)8.429,(float)8.502,(float)8.556,(float)8.63,(float)8.688,(float)8.775,(float)8.812,(float)8.901,(float)8.939,(float)9.021,(float)9.065,(float)9.129,(float)9.194,(float)9.277,(float)9.32,(float)9.408,(float)9.446,(float)9.51,(float)9.577,(float)9.666,(float)9.701,(float)9.766,(float)9.828,(float)9.909,(float)9.955,(float)10.018,(float)10.086,(float)10.182,(float)10.209,(float)10.273,(float)10.336,(float)10.4,(float)10.464,(float)10.527,(float)10.591,(float)10.661,(float)10.718,(float)10.802,(float)10.845,(float)10.909,(float)10.972,(float)11.043,(float)11.099,(float)11.163,(float)11.226,(float)11.29,(float)11.353,(float)11.441,(float)11.481,(float)11.573,(float)11.607,(float)11.671,(float)11.738,(float)11.8,(float)11.862,(float)11.948,(float)11.989};
//		float[] dataX = {(float)0.,(float)0.17458,(float)0.06403,(float)0.04106,(float)0.00955,(float)0.07471,(float)0.00581,(float)0.17564,(float)0.22905,(float)-0.01662,(float)-0.18805,(float)-0.00113,(float)0.13773,(float)-0.04118,(float)-0.01288,(float)0.15161,(float)0.04427,(float)-0.08444,(float)-0.29112,(float)-0.00006,(float)0.17992,(float)0.03358,(float)0.01115,(float)-0.25267,(float)-0.48605,(float)-0.25481,(float)-0.02356,(float)0.00208,(float)0.02077,(float)0.06723,(float)0.06349,(float)0.01009,(float)0.048,(float)0.0213,(float)0.02824,(float)0.02611,(float)0.03946,(float)0.05067,(float)0.04533,(float)0.07684,(float)0.09233,(float)-0.04973,(float)-0.14799,(float)-0.08337,(float)0.0464,(float)0.11476,(float)-0.02356,(float)-0.04759,(float)0.04373,(float)-0.04492,(float)-0.13251,(float)-0.08177,(float)0.00421,(float)-0.0305,(float)-0.02409,(float)0.11369,(float)0.07791,(float)-0.03905,(float)-0.00327,(float)-0.01234,(float)-0.01395,(float)-0.01128,(float)-0.00914,(float)-0.04759,(float)-0.04652,(float)0.00848,(float)-0.00914,(float)-0.04652,(float)-0.0305,(float)0.02451,(float)-0.01662,(float)0.01329,(float)-0.00754,(float)0.03145,(float)-0.00754,(float)-0.01395,(float)0.01222,(float)-0.00647,(float)0.02077,(float)-0.01341,(float)-0.00113,(float)-0.01608,(float)-0.01929,(float)0.00261,(float)-0.01288,(float)-0.00487,(float)-0.00327,(float)-0.04065,(float)-0.0807,(float)-0.03958,(float)0.01756,(float)-0.01395,(float)-0.01929,(float)-0.00594,(float)-0.06682,(float)-0.08284,(float)0.01329,(float)-0.06682,(float)-0.19125,(float)-0.07857,(float)-0.0775,(float)0.06189,(float)-0.09138,(float)-0.13037,(float)-0.08925,(float)0.01703,(float)0.06509,(float)0.03679,(float)-0.0321,(float)0.0229,(float)0.03358,(float)0.01543,(float)0.02344,(float)0.01276,(float)-0.0054,(float)-0.07002,(float)-0.06041,(float)0.01756,(float)0.01649,(float)0.05121,(float)0.18846,(float)-0.23772,(float)-0.15814,(float)1.14282,(float)-0.07857,(float)-0.08978,(float)-0.32797,(float)-0.91704,(float)-0.90689,(float)0.00795,(float)0.37485,(float)-0.28632,(float)0.02504,(float)-0.07643,(float)-0.01662,(float)-0.02516,(float)0.00047,(float)0.02611,(float)0.00955,(float)-0.01074,(float)0.00742,(float)-0.00914,(float)-0.02997,(float)-0.01929,(float)-0.03745,(float)-0.03584,(float)-0.0305,(float)0.00047,(float)-0.09619,(float)-0.09032,(float)-0.00754,(float)0.00421,(float)-0.01448,(float)-0.00754,(float)-0.00166,(float)-0.02089,(float)-0.01929,(float)-0.01555,(float)0.00742,(float)-0.02249,(float)-0.01929,(float)-0.01662,(float)0.01329,(float)-0.05774,(float)-0.20407,(float)-0.09299,(float)0.07524,(float)0.05495,(float)0.03198,(float)0.22638,(float)0.25201,(float)0.18098,(float)-0.25427,(float)-0.297,(float)0.09821,(float)0.08058,(float)0.06296,(float)-0.101,(float)-0.16028,(float)-0.101,(float)0.07684,(float)0.07791,(float)0.12277,(float)0.0213,(float)0.01489,(float)0.00101,(float)-0.07323,(float)-0.14372,(float)-0.16776,(float)-0.0273};
//		float[] dataY = {(float)0.,(float)0.16397,(float)0.1127,(float)0.04755,(float)0.02138,(float)0.02458,(float)0.03793,(float)0.04167,(float)0.133,(float)0.29161,(float)0.30283,(float)0.25102,(float)0.22539,(float)0.2852,(float)0.23073,(float)-0.01494,(float)0.16824,(float)0.12766,(float)0.09294,(float)0.14902,(float)-0.04805,(float)-0.14738,(float)-0.1132,(float)-0.0881,(float)-0.06995,(float)-0.04698,(float)-0.00746,(float)-0.03256,(float)-0.01868,(float)0.02565,(float)0.05556,(float)0.00536,(float)-0.04057,(float)-0.06728,(float)-0.02242,(float)0.03633,(float)0.03526,(float)0.01871,(float)0.00536,(float)0.00642,(float)-0.02615,(float)-0.09665,(float)-0.16501,(float)-0.18584,(float)-0.16875,(float)-0.16287,(float)-0.17889,(float)-0.16608,(float)-0.11214,(float)-0.0849,(float)-0.08383,(float)-0.0379,(float)0.00108,(float)0.00322,(float)0.00536,(float)0.0374,(float)0.06303,(float)0.03206,(float)0.00055,(float)-0.01494,(float)-0.00906,(float)0.00856,(float)0.0107,(float)0.01497,(float)0.00749,(float)0.00749,(float)0.00002,(float)-0.00159,(float)-0.00212,(float)-0.00426,(float)-0.00693,(float)-0.00319,(float)-0.00426,(float)-0.00266,(float)-0.01387,(float)0.00055,(float)0.00269,(float)-0.01013,(float)-0.01067,(float)-0.01067,(float)0.00162,(float)0.00536,(float)0.0139,(float)0.02672,(float)0.02565,(float)0.03099,(float)0.03366,(float)0.02992,(float)0.03259,(float)0.07799,(float)0.12659,(float)0.1143,(float)0.07372,(float)0.04915,(float)0.05556,(float)0.0876,(float)0.12125,(float)0.08813,(float)0.12338,(float)0.11911,(float)0.11964,(float)0.07211,(float)-0.06194,(float)-0.04858,(float)-0.01814,(float)-0.04218,(float)-0.00212,(float)0.06357,(float)0.06197,(float)0.02725,(float)-0.02989,(float)-0.00586,(float)-0.0112,(float)-0.00266,(float)0.00429,(float)0.01817,(float)0.0107,(float)0.00375,(float)0.02405,(float)0.00108,(float)-0.12015,(float)-0.17569,(float)-1.94662,(float)-3.64226,(float)-3.45908,(float)-1.79335,(float)0.81125,(float)3.00676,(float)3.7005,(float)1.85854,(float)0.76585,(float)0.27879,(float)-0.02936,(float)0.00215,(float)-0.04591,(float)-0.01707,(float)-0.00533,(float)-0.01334,(float)0.00269,(float)0.01764,(float)0.0107,(float)-0.00426,(float)-0.00426,(float)-0.00105,(float)0.00269,(float)0.00162,(float)0.00055,(float)0.02138,(float)0.07959,(float)0.09615,(float)0.01176,(float)0.00055,(float)0.01016,(float)0.00642,(float)0.01657,(float)0.02084,(float)0.0139,(float)0.00482,(float)0.00162,(float)0.00749,(float)0.0107,(float)-0.01334,(float)-0.02455,(float)-0.05125,(float)-0.05606,(float)-0.09184,(float)0.00536,(float)-0.0112,(float)-0.0598,(float)-0.09558,(float)0.01497,(float)0.09081,(float)0.282,(float)0.21524,(float)0.13139,(float)0.02672,(float)0.05396,(float)0.0107,(float)0.08333,(float)0.04221,(float)0.03419,(float)0.01871,(float)0.01016,(float)0.04007,(float)0.02298,(float)0.00909,(float)-0.00159,(float)0.00269,(float)-0.03203,(float)0.00909};
//		
//		RawData rawData = new RawData(time, dataX, dataY);
//		
//		String zeroesStr = Arrays.toString(rawData.findZeroesX());
//		String extremaStr = Arrays.toString(rawData.findExtremaX());
//		String critPtStr = Arrays.toString(rawData.findCriticalPointsX()[0]);
//		String typeStr = Arrays.toString(rawData.findCriticalPointsX()[1]);
//		
//		List<Character> patternCode = Pattern.generatePatternCodeXY(rawData);
//		String patternStr = patternCode.toString();
//		String smoothTStr = Arrays.toString(rawData.getTime());
//		String smoothXStr = Arrays.toString(rawData.getX());
//		String smoothYStr = Arrays.toString(rawData.getY());
//				
//		System.out.print("Zeroes:\n");
//		System.out.print(zeroesStr);
//		
//		System.out.print("\nExtrema:\n");
//		System.out.print(extremaStr);
//		
//		System.out.print("\nCritical Points:\n");
//		System.out.print(critPtStr);
//		
//		System.out.print("\nType Points:\n");
//		System.out.print(typeStr);
//		
//		System.out.print("\nSmooth Time:\n");
//		System.out.print(smoothTStr);
//		System.out.print("\nSmooth X:\n");
//		System.out.print(smoothXStr);
//		System.out.print("\nSmooth Y:\n");
//		System.out.print(smoothYStr);
//		
//		System.out.print("\nPattern:\n");
//		System.out.print(patternStr);
//		
//		
//		
////		Pattern pattern = new Pattern(rawData);
////		System.out.print(pattern.isEqual(pattern));
//	}
//
//}
