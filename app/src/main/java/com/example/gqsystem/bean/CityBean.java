package com.example.gqsystem.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * @author : devel
 * @date : 2020/4/14 15:05
 * @desc :
 */
public class CityBean implements IPickerViewData {

    /**
     * code : 110000
     * value : 北京市
     * label : 北京市
     * children : [{"code":"110000","value":"北京市","label":"北京市","children":[{"code":"110101","value":"东城区","label":"东城区"},{"code":"110102","value":"西城区","label":"西城区"},{"code":"110105","value":"朝阳区","label":"朝阳区"},{"code":"110106","value":"丰台区","label":"丰台区"},{"code":"110107","value":"石景山区","label":"石景山区"},{"code":"110108","value":"海淀区","label":"海淀区"},{"code":"110109","value":"门头沟区","label":"门头沟区"},{"code":"110111","value":"房山区","label":"房山区"},{"code":"110112","value":"通州区","label":"通州区"},{"code":"110113","value":"顺义区","label":"顺义区"},{"code":"110114","value":"昌平区","label":"昌平区"},{"code":"110115","value":"大兴区","label":"大兴区"},{"code":"110116","value":"怀柔区","label":"怀柔区"},{"code":"110117","value":"平谷区","label":"平谷区"},{"code":"110118","value":"密云区","label":"密云区"},{"code":"110119","value":"延庆区","label":"延庆区"}]}]
     */

    private String value;
    private String label;
    private List<ChildrenBeanX> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    @Override
    public String getPickerViewText() {
        return this.value;
    }

    public static class ChildrenBeanX {
        /**
         * code : 110000
         * value : 北京市
         * label : 北京市
         * children : [{"code":"110101","value":"东城区","label":"东城区"},{"code":"110102","value":"西城区","label":"西城区"},{"code":"110105","value":"朝阳区","label":"朝阳区"},{"code":"110106","value":"丰台区","label":"丰台区"},{"code":"110107","value":"石景山区","label":"石景山区"},{"code":"110108","value":"海淀区","label":"海淀区"},{"code":"110109","value":"门头沟区","label":"门头沟区"},{"code":"110111","value":"房山区","label":"房山区"},{"code":"110112","value":"通州区","label":"通州区"},{"code":"110113","value":"顺义区","label":"顺义区"},{"code":"110114","value":"昌平区","label":"昌平区"},{"code":"110115","value":"大兴区","label":"大兴区"},{"code":"110116","value":"怀柔区","label":"怀柔区"},{"code":"110117","value":"平谷区","label":"平谷区"},{"code":"110118","value":"密云区","label":"密云区"},{"code":"110119","value":"延庆区","label":"延庆区"}]
         */

        private String code;
        private String value;
        private String label;
        private List<ChildrenBean> children;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean implements IPickerViewData {
            /**
             * code : 110101
             * value : 东城区
             * label : 东城区
             */

            private String code;
            private String value;
            private String label;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            @Override
            public String getPickerViewText() {
                return value;
            }
        }
    }
}
