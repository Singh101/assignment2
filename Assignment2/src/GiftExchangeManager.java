import java.io.PrintStream;
import java.util.*;

public class GiftExchangeManager {
    // TODO add your fields here
    private GiftNode participants;
    private GiftNode chosen;


    public GiftExchangeManager(List<String> nameList) {
        // TODO Auto-generated constructor stub
        // throw exception for size 0 list or null list
        if (nameList.size() == 0 || nameList == null) {
            throw new IllegalArgumentException();
        } else {
            participants = new GiftNode(nameList.get(0));
            //nextNode acts as reference node
            GiftNode nextNode = participants;

            //loop through list to add GiftNodes for each name
            for (int m = 1; m < nameList.size(); m++) {
                nextNode.next = new GiftNode(nameList.get(m));
                nextNode = nextNode.next;
            }
        }
    }

    public void printRemainingParticipants(PrintStream stream) {
        // TODO Auto-generated method stub
        //current acting as reference node
        GiftNode current = this.participants;

        //loops until last node where current.next = null
        while (current.next != null) {
            stream.println("  " + current.name + " chooses before " + current.next.name);
            current = current.next;
        }

        stream.println("  " + current.name + " chooses last");

    }

    public void printAlreadyChosen(PrintStream stream) {
        // TODO Auto-generated method stub
        //current acting as reference node
        GiftNode current = chosen;
        while (current != null) {
            stream.println("  " + current.name + " opened a " + current.gift);
            current = current.next;
        }

    }

    public boolean remainingParticipantsContains(String name) {
        // TODO Auto-generated method stub
        //initially false indicating not found
        boolean found = false;
        GiftNode current = participants;
        while (current != null) {
            //if name is found boolean found turns true
            if (current.name.compareToIgnoreCase(name) == 0) {
                found = true;
            }
            current = current.next;
        }
        return found;
    }

    public boolean alreadyChosenContains(String name) {
        // TODO Auto-generated method stub
        //initially false indicating not found
        boolean found = false;
        GiftNode current = chosen;
        while (current != null) {

            //if name is found boolean found turns true
            if (current.name.compareToIgnoreCase(name) == 0) {
                found = true;
            }
            current = current.next;
        }
        return found;
    }

    public boolean isGameOver() {
        // TODO Auto-generated method stub
        //initially set to false to indicate game isn't over
        boolean isOver = false;
        if (participants == null) {
            //set to true if there are no more remaining participants
            isOver = true;
        }
        return isOver;
    }

    public void openGift(String gift) {
        //checking to see if game is over
        if (isGameOver() == true) {
            throw new IllegalStateException();
        } else {
            // TODO Auto-generated method stub
            //reference node to next participant
            GiftNode opened = participants;
            //reference node to next node which becomes new front for participants node
            GiftNode newPFront = participants.next;

            //opened placed at front of chosen node
            opened.next = chosen;
            chosen = opened;

            //setting gift
            chosen.gift = gift;

            //setting new front for participants node
            participants = newPFront;
        }
    }

    public void stealFrom(String name) {
        // TODO Auto-generated method stub
        //if name game is over & name isn't found, or only the game is over, throw IllegalStateException
        if ((isGameOver() && !alreadyChosenContains(name)) || (isGameOver())) {
            throw new IllegalStateException();
            //if only name isn't found, throw IllegalArgumentException
        } else if (!alreadyChosenContains(name)) {
            throw new IllegalArgumentException();
        } else {
            //reference node to iterate through chosen linked list
            GiftNode current = chosen;
            //reference node to person stealing gift
            GiftNode stealer = participants;
            //reference node to unchanged portion of participants list
            GiftNode pFront = participants.next;
            //reference node to front of chosen list
            GiftNode cFront = chosen;

            //testing very first node
            if (current.name.compareToIgnoreCase(name) == 0) {
                //person who's gift is getting stolen
                GiftNode stolenFrom = current;

                //unchanged portion of chosen list
                cFront = chosen.next;

                //switching stealer to chosen list
                stealer.next = cFront;
                chosen = stealer;

                //switching person that is stolen from to participants list
                stolenFrom.next = pFront;
                participants = stolenFrom;

                //switching gift
                chosen.gift = participants.gift;
                participants.gift = null;
            } else {
                //for nodes in the middle (not first /last)
                while (current.next != null) {

                    if (current.next.name.compareToIgnoreCase(name) == 0) {
                        GiftNode stolenFrom = current.next;

                        //switching gift
                        stealer.gift = stolenFrom.gift;
                        stolenFrom.gift = null;

                        //reconfiguring chosen list
                        current.next = current.next.next;

                        //adding person who got stolen from to participants list
                        stolenFrom.next = pFront;
                        participants = stolenFrom;

                        //adding person who stole to chosen list
                        stealer.next = cFront;
                        chosen = stealer;

                    }
                    current = current.next;

                }
                //testing very last node:
                if (current.name.compareToIgnoreCase(name) == 0) {
                    current = chosen;

                    //locating node right before last
                    while (current.next.next != null) {
                        current = current.next;
                    }
                    GiftNode stolenFrom = current.next;

                    //switching gifts
                    stealer.gift = stolenFrom.gift;
                    stolenFrom.gift = null;

                    //reconfiguring chosen list
                    current.next = null;
                    stealer.next = cFront;
                    chosen = stealer;

                    //reconfiguring participants list
                    stolenFrom.next = pFront;
                    participants = stolenFrom;


                }

            }
        }

    }
}

