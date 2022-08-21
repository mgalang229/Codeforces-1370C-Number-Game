import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*

rules:
- divide n by any of its odd divisors > 1
- subtract 1 from n iff n is > 1

if n is odd and > 1, then simply divide it by itself
in this case first player always wins

our goal is to turn it into 1

n = 6
[2, 3]
6 / 2 = 3
6 / 3 = 2
second player wins

n = 12
[2, 6, 3, 4]
12 / 3 = 4
4 - 1 = 3
3 / 3 = 1
first player wins

in a normal game, avoid turning it into 2

only powers of 2 have no odd divisors

there are some cases where first player wins without powers of 2 present

 */

public class Main {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			if (n == 1 || (isPowerTwo(n) && n != 2)) {
				out.println("FastestFinger");
				continue;
			}
			if (n % 2 == 1 || n == 2) {
				out.println("Ashishgup");
				continue;
			}
			if (firstCanWin(n)) {
				out.println("Ashishgup");
				continue;
			}
			out.println("FastestFinger");
		}
		out.close();
	}
	
	static boolean firstCanWin(int n) {
		int num = 0;
		boolean checker = false;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				if (i == 2) {
					if (isPowerTwo(n / i)) {
						return true;
					}
				} else {
					if (isPowerTwo(i) || isPowerTwo(n / i)) {
						return true;
					}
					if (i % 2 == 0) {
						checker = !secondCanWin(i);
					}
					if (n / i % 2 == 0) {
						checker = !secondCanWin(n / i);
					}
					if (checker) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	static boolean secondCanWin(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				if (i == 2) {
					if (isPowerTwo(n / i)) {
						return true;
					}
				} else {
					if (isPowerTwo(i) || isPowerTwo(n / i)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	static boolean isPowerTwo(int n) {
		int copy = n;
		while (copy > 1) {
			if (copy % 2 == 1) {
				return false;
			}
			copy /= 2;
		}
		return true;
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
