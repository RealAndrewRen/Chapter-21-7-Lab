package teletext;

/**
 * Implements the doubly-linked list of messages for Teletext
 */

import java.awt.Graphics;

public class TeletextList {
	private ListNode2 heading, topNode;

	/**
	 * Creates a circular list of headlines. First creates a circular list with one
	 * node, "Today's headlines:". Saves a reference to that node in heading. Adds a
	 * node holding an empty string before heading and another node holding an empty
	 * string after heading. Appends all the strings from headlines to the list,
	 * after the blank line that follows heading, preserving their order. Sets
	 * topNode equal to heading.
	 */
	public TeletextList(String[] headlines) {
		heading = new ListNode2("Today's headlines:");
		heading.setPrevious(new ListNode2(""));
		heading.getPrevious().setNext(heading);
		heading.setNext(new ListNode2(""));
		heading.getNext().setPrevious(heading);
		ListNode2 node = heading.getNext();
		for (int i = 0; i < headlines.length; i++) {
			node.setNext(new ListNode2(headlines[i]));
			node.getNext().setPrevious(node);
			node = node.getNext();
		}
		topNode = heading;
		node.setNext(heading.getPrevious());
	}

	/**
	 * Inserts a node with msg into the headlines list after the blank line that
	 * follows heading.
	 */
	public void insert(String msg) {
		ListNode2 node = heading.getNext().getNext();
		heading.getNext().setNext(new ListNode2(msg));
		heading.getNext().getNext().setNext(node);
		heading.getNext().getNext().setPrevious(heading.getNext());
		node.setPrevious(heading.getNext().getNext());
	}

	/**
	 * Deletes the node that follows topNode from the headlines list, unless that
	 * node happens to be heading or the node before or after heading that holds a
	 * blank line.
	 */
	public void delete() {
		ListNode2 node = topNode.getNext();
		if (node.getValue().equals("")) {
			node = node.getNext();
		} else {
		}
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
	}

	/**
	 * Scrolls up the headlines list, advancing topNode to the next node.
	 */
	public void scrollUp() {
		topNode = topNode.getNext();
	}

	/**
	 * Adds a new node with msg to the headlines list before a given node. Returns a
	 * referenece to the added node.
	 */
	private ListNode2 addBefore(ListNode2 node, String msg) {
		ListNode2 newNode = new ListNode2(msg, node.getPrevious(), node);
		node.getPrevious().setNext(newNode);
		node.setPrevious(newNode);
		return newNode;
	}

	/**
	 * Adds a new node with msg to the headlines list after a given node. Returns a
	 * referenece to the added node.
	 */
	private ListNode2 addAfter(ListNode2 node, String msg) {
		ListNode2 newNode = new ListNode2(msg);
		newNode.setNext(node.getNext());
		node.setNext(newNode);
		newNode.setPrevious(node);
		newNode.getNext().setPrevious(newNode);
		return newNode;
	}

	/**
	 * Removes a given node from the list.
	 */
	private void remove(ListNode2 node) {
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
	}

	/**
	 * Draws nLines headlines in g, starting with topNode at x, y and incrementing y
	 * by lineHeight after each headline.
	 */
	public void draw(Graphics g, int x, int y, int lineHeight, int nLines) {
		ListNode2 node = topNode;
		for (int k = 1; k <= nLines; k++) {
			g.drawString((String) node.getValue(), x, y);
			y += lineHeight;
			node = node.getNext();
		}
	}
}
