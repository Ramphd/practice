package work.liyue;

/**
 * Created by hzliyue1 on 2016/7/21,0021, 19:18:10.
 */

    public enum EnumC {

        DIMPLEUSER("01"),

        DIMPLEPRODUCT( "02"),

        DIMPLEGROUPON( "03"),

        DIMPLECOMMENT("04")
        ;

        private String preFix;

        EnumC(String preFix) {
            this.preFix = preFix;
        }
        /**
         * Getter method for property <tt>preFix</tt>.
         *
         * @return property value of code
         */
        public String getpreFix() {
            return preFix;
        }

        /**
         * Setter method for property <tt>code</tt>.
         *
         * @param preFix value to be assigned to property code
         */
        public void setpreFix(String preFix) {
            this.preFix = preFix;
        }

    }

