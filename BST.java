
public class BST {
	private Node root; // The root node of the tree.
	private Node pred;
	private Node succ;

	public BST() 
	{
		root = null;
		pred = null;
		succ = null;
	}

	/**
		Inserts a time, along with the req_id. The tree is keyed on time, while req_id provides a pointer to the request.
		This is a public wrapper function that calls the recursive insert method.
		Note that the insert method should return the root node of the subtree in which we insert the key (and its value).
	**/
	public void insert(int time, int req_index) 
	{
		// TODO: Code for insert here.
		root = insert(root, time, req_index);
	}
	
	private Node insert(Node T, int time, int req_index)
	{
		if (T == null)
		{
			return new Node(time, req_index);
		}
		if (time > T.getTime())
		{
			T.setRight(insert(T.getRight(), time, req_index));
		}
		else if (time < T.getTime())
		{
			T.setLeft(insert(T.getLeft(), time, req_index));
		}
		return T;
	}
	

	/**
		Returns a pointer to the Node that is the predecessor of time. The predecessor element is the largest
		element within the tree that is smaller or equal to time. This is the deepest ancestor with this property.
		Please return the predecessor element. You may return null if time does not have a predecessor.
	**/
	public Node pred(int time) 
	{
		// TODO: code for pred here.
		pred = null;
		if (root == null)
		{
			return null;
		}
		else
		{
			return pred(root, time);
		}
	}
	
	private Node pred(Node x, int time)
	{
		if (x == null)
		{
			return pred;
		}
		if (x.getTime() < time)
		{
			pred = x;
			return pred(x.getRight(), time);
		}
		else if (x.getTime() == time)
		{
			if (x.getLeft() != null)
			{
				x = x.getLeft();
				while (x.getRight() != null)
				{
					x = x.getRight();
				}
				return x;
			}
		}
		else
		{
			return pred(x.getLeft(), time);
		}
		return pred;
	}
	
	
	

	/**
		Returns a pointer to the Node that is the successor of time. The successor element is the largest
		element within the tree that is larger or equal to time. This is the deepest ancestor with this property.
		Please return the successor element. You may return null if time does not have a successor.
	**/
	public Node succ(int time) 
	{
		// TODO: code for succ here.
		succ = null;
		if (root == null)
		{
			return null;
		}
		else
		{
			return succ(root, time);
		}
	}
	
	private Node succ(Node z, int time)
	{
		if (z == null)
		{
			return succ;
		}
		if (z.getTime() < time)
		{
			return succ(z.getRight(), time);
		}
		else if (z.getTime() == time)
		{
			if (z.getRight() != null)
			{
				z = z.getRight();
				while (z.getLeft() != null)
				{
					z = z.getLeft();
				}
				return z;
			}
		}
		else
		{
			succ = z;
			return succ(z.getLeft(), time);
		}
		if (succ != null && succ.getTime() > time)
		{
			return succ;
		}
		else
		{
			return null;
		}
	}

	/**
		Returns the minimum element in the binary search tree or null if the tree is empty.
	**/
	public Node min() 
	{
		// TODO: Code for min here.
		if (root == null)
		{
			return null;
		}
		else
		{
			return min(root);
		}
	}
	
	/**
		Returns the minimum element in the binary search tree or null if the tree is empty.
	**/
	private Node min(Node y)
	{
		if (y.getLeft() == null)
		{
			return y;
		}
		else
		{
			return min(y.getLeft());
		}
	}
	

	/**
		Returns the maximum element in the binary search tree or null if the tree is empty.
	**/
	public Node max() 
	{
		// TODO: Code for max here.
		if (root == null)
		{
			return null;
		}
		else
		{
			return max(root);
		}
	}
	
	/**
		Returns the maximum element in the binary search tree or null if the tree is empty.
	**/
	
	private Node max(Node w)
	{
		if (w.getRight() == null)
		{
			return w;
		}
		else
		{
			return max(w.getRight());
		}
	}

	/**
		Remove the node that contains this time. If this time is not present in the structure, this method does nothing.
	**/
	public void delete(int time) 
	{
		// TODO: Code for delete here.
		root = delete(root, time);
	}
	
	/**
		Remove the node that contains this time. If this time is not present in the structure, this method does nothing.
	**/
	private Node delete(Node c, int time)
	{
		if (c == null)
		{
			return null;
		}
		if (c.getTime() == time)
		{
			if (c.getLeft() == null)
			{
				return c.getRight();
			}
			else if (c.getRight() == null)
			{
				return c.getLeft();
			}
			c.setTime(firstInput(c.getRight()));
			c.setRight(delete(c.getRight(), c.getTime()));
		}
		else if (c.getTime() < time)
		{
			c.setRight(delete(c.getRight(), time));
		}
		else
		{
			c.setLeft(delete(c.getLeft(), time));
		}
		return c;
	}
	
	
	private int firstInput(Node b)
	{
		int min = b.getTime();
		while (b.getLeft() != null)
		{
			min = b.getLeft().getTime();
			b = b.getLeft();
		}
		return min;
	}

	/**
		Prints the contents of the tree in sorted order.
	**/
	public void print() 
	{
		print(root);
	}
	
	private void print(Node a)
	{
		if (a == null)
		{
			return;
		}
		print(a.getLeft());
		System.out.println(a.getTime());
		print(a.getRight());
	}
	
//	public static void main(String[] args)
//	{
//		BST bst = new BST();
//		bst.print(bst.root);
//		System.out.println("shuffle");
//		
//		bst.insert(8,8);
//		bst.insert(1,1);
//		bst.insert(2,2);
//		bst.insert(3,3);
//		bst.insert(4,4);
//		bst.insert(9,9);
//		bst.print();
//		bst.delete(9);
//		System.out.println(bst.max());
//		System.out.println(bst.min());
//	}
}
