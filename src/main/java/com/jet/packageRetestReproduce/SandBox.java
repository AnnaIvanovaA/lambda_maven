package com.jet.packageRetestReproduce;

import java.text.MessageFormat;

public class SandBox {

        public static void main(String[] argv) {
            SandBox sandBox1 = new SandBox(null);
            SandBox sandBox2 = new SandBox(sandBox1);
            SandBox sandBox3 = new SandBox(sandBox1);
            SandBox sandBox4 = new SandBox(sandBox2);
            sandBox1.comeIn(); // Breakpoint here.
            sandBox2.comeIn();
            sandBox3.comeIn();
            sandBox4.comeIn();
        }

        private SandBox relationship;

        public SandBox(SandBox relationship) {
            this.relationship = relationship;
        }

        public void comeIn() {
            System.out.println(MessageFormat.format("{0} -> {1}", this, this.relationship));
        }

}
