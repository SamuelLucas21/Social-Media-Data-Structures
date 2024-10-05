package Body;

import java.util.ArrayList;

public class Chat {
        private class Element{
            private Message message;
            private Element prox, ant;

                public Element(Message newMessage){
                    this.message=newMessage;
                    this.prox=null;
                    this.ant=null;
                }

            public Message getMessage() {
                return message;
            }

            public Element getProx() {
                return prox;
            }

            public void setProx(Element prox) {
                this.prox = prox;
            }

            public Element getAnt() {
                return ant;
            }

            public void setAnt(Element ant) {
                this.ant = ant;
            }

        }
        private class Iterator{
            private Element first=null;
                public Iterator(Element newHead){
                    this.first=newHead;
                    this.first.setAnt(new Element(null));
                    this.first.getAnt().setProx(newHead);
                    this.first=first.getAnt();
                }
            
            public boolean HasNext(){
                if(getProxElement()!=null){
                    return true;
                }else{
                    return false;
                }
            }
            public Element getElement(){return this.first;}

            private Element getProxElement(){
                this.first=this.first.getProx();
                return first;
            }
        }

    private Element head, tail;
    private static short size =0;
    
    public Message getFirstMessage(){return this.head.getMessage();}
    public Message getLastMessage(){return this.tail.getMessage();}

    public void add(Message newChat){
        if(this.head!=null){
            this.tail.setProx(new Element(newChat));
            this.tail.getProx().setAnt(tail);
            this.tail=this.tail.getProx();
            ++size;
        }else{
            this.head=new Element(newChat);
            this.tail=head;
            ++size;
        }
    }
    public void remove(short idMessage){
        Element aux=this.head;
        if(aux!=null){
            if(aux.getMessage().getId()==idMessage){
                this.head=this.head.getProx();
                --size;
                return;
            }else if(this.tail.getMessage().getId()==idMessage){
                this.tail=this.tail.getAnt();
                --size;
                return;
            }else{
                while(aux!=null){
                    if(aux.getMessage().getId()==idMessage){
                        aux.getProx().setAnt(aux.getAnt());
                        aux.getAnt().setProx(aux.getProx());
                        aux=null;
                        --size;
                        return;
                    }
                    aux=aux.getProx();
                }
            }
        }
    }
    
    public ArrayList<Message> getMessages(){
        ArrayList<Message> message = new ArrayList<>();
        if(this.head!=null){
            Iterator iterator = new Iterator(head);
            while(iterator.HasNext()){
                message.add(iterator.getElement().getMessage());
            }
            return message;
        }else{
            return null;
        }
    }

    public void editMessage(short idMessage, String newMessage){
        if(this.head!=null){
            Iterator iterator= new Iterator(head);
            while(iterator.HasNext()) {
                if(iterator.getElement().getMessage().getId()==idMessage){
                    iterator.getElement().getMessage().setTxtMessage(newMessage);
                    return;
                }
            }
        }
    }

    public int size(){return(int) size;}
}
