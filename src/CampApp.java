import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CampApp {
	public static void main(String[] args) throws IOException {
		// csvファイルの読み込み
		String path = "c:/Users/sasaki/Desktop/0609PMeclipse/Warikan/data.csv";
		FileInputStream fis = new FileInputStream(path);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// Map用意
		Map<String, Integer> names = new HashMap<String, Integer>();
		Map<String, Integer> items = new HashMap<String, Integer>();

		// 1行ずつ処理を行う
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] datas = line.split(",");
			// 名前と買ったモノ、金額を変数に代入する
			String name = datas[0];
			String item = datas[1];
			int price = Integer.parseInt(datas[2]);

			// 名前と金額のペア（Map）
			if (names.containsKey(name)) {
				names.put(name, names.get(name) + price);
			} else {
				names.put(name, price);
			}
			// 買ったモノと金額のペア(Map)
			if (items.containsKey(item)) {
				items.put(item, items.get(item) + price);
			} else {
				items.put(item, price);
			}	
		}
		br.close();

		System.out.println("キャンプ会計");
		System.out.println("------------------");
		int total = 0;
		for (String key : items.keySet()) {
			System.out.printf("%s:%d\n", key, items.get(key));
			total += items.get(key);
		}
		System.out.println();

		int perPrice = total / names.size();
		System.out.printf("個人別会計(１人あたり:%d円)\n", perPrice);
		System.out.println("------------------");
		for (String key : names.keySet()) {
			int money = names.get(key) - perPrice;
			System.out.printf("%s:%s%d\n", key, money < 0 ? "-" : "+", Math.abs(money));
		}
	}
}
