package pl.gren.oze_app.model;

import org.springframework.expression.spel.ast.OpNE;

public enum HeatPumpTypes {
        MONOBLOK {
            public String toString() {
                return "MONOBLOK";
            }
        },

        SPLIT {
            public String toString() {
                return "SPLIT";
            }
        },
        ALL_IN_ONE {
        public String toString() {
            return "ALL IN ONE";
        }
    }
    }
