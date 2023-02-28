import java.util.Scanner;

public class RunwayReservation {
	private static int n;
	private static int k;

	@SuppressWarnings("resource")
	public static void main(String [] args) {
		Scanner kb = new Scanner(System.in);
		n = kb.nextInt(); // The total number of requests.
		k = kb.nextInt(); // Grace time between requests.

		// Variables for getting the input as well as the rest of the problem.
		String cmd;
		int time = 0;
		BST biTree = new BST();
		String flightname = null;
		String flightnumber = null;
		String source = null;
		String destination = null;
		int curtime = 0; // Current time, initialized to 0.
		Node pred;
		Node succ;
		int look;

		// An array of requests. This is the data stored outside of our binary search biTree.
		Requests [] reqs = new Requests[n];
		int i = 0;

		// Reading the input. All requests are read from the input file and stored in array reqs.
		while(kb.hasNext()) {
			cmd = kb.next();

			if (cmd.equals("r")) {
				time = kb.nextInt();
				flightname = kb.next();
				flightnumber = kb.next();
				source = kb.next();
				destination = kb.next();

				reqs[i++] = new Requests(cmd, time, flightname, flightnumber, source, destination);
			}
			else {
				time = kb.nextInt();
				reqs[i++] = new Requests(cmd, time);
			}
			kb.nextLine();
		}

		// TODO: Code to process each request and solve the Runway Reservation problem.
		
		for (int a = 0; a < n; a++)
		{
			if (reqs[a].getCommand().equals("r"))
			{
				look = reqs[a].getTime();
				pred = biTree.pred(look);
				succ = biTree.succ(look);
				
				if (pred != null && succ != null)
				{
					if (look - k > pred.getTime() && look + k < succ.getTime())
					{
						biTree.insert(look, a);
					}
				}
				
				else if (pred == null && succ == null)
				{
					biTree.insert(look, a);
				}
				
				else if (pred == null && succ != null)
				{
					if (look + k < succ.getTime())
					{
						biTree.insert(look, a);
					}
				}
				
				else if (pred != null && succ == null)
				{
					if (look - k > pred.getTime())
					{
						biTree.insert(look, a);
					}
				}	
			}
			
			else if (reqs[a].getCommand().equals("t"))
			{
				curtime += reqs[a].getTime();
				System.out.println("Current time = " + curtime + " units");
				
				while (biTree.min().getTime() < curtime)
				{
					System.out.println(reqs[biTree.min().getReq_index()].getAirline());
					biTree.delete(biTree.min().getTime());
				}
			}
		}
		
		int maxTime = biTree.max().getTime();
		System.out.println("Current time = " + maxTime + " units");
		Node min = biTree.min();
		
		while (min != null)
		{
			System.out.println(reqs[min.getReq_index()].getAirline());
			biTree.delete(min.getTime());
			min = biTree.succ(min.getTime());
		}
	}
}
