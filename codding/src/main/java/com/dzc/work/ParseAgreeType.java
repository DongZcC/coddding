package com.dzc.work;

public class ParseAgreeType {

    public static void main(String[] args) {
        String agreeType = "271045 H\n" +
                "271047 H\n" +
                "271051 I\n" +
                "271017 i,k\n" +
                "271058 J\n" +
                "271060 L\n" +
                "271029 d\n" +
                "271033 w";


        String[] types = agreeType.split("\n");

        for (String type : types) {
            String[] s = type.split(" ");
            String sql = "prompt\n" +
                    "prompt 新增初始化协议数据 " + s[1] + "\n" +
                    "declare v_rowcount number(5);\n" +
                    "begin\n" +
                    "  select count(*) into v_rowcount from dual\n" +
                    "    where exists (select 1 from hs_elg.elg_busin_agreement_arg where acpt_busin_id = '" + s[0] + "' and agree_type = '" + s[1] + "');\n" +
                    "  if v_rowcount = 0 then\n" +
                    "\tinsert into elg_busin_agreement_arg (acpt_busin_kind, acpt_busin_id, template_name, template_dir, agreement_version, use_flag, modify_date, modify_time, remark, agree_type, organ_flag, file_category, agree_duration, signed_condition)\n" +
                    "\t\tvalues  ('27', '" + s[0] + "', ' ', ' ', ' ', '0', to_number(to_char(sysdate, 'YYYYMMDD')), to_number(to_char(sysdate, 'HH24MISS')), ' ', '" + s[1] + "', '!', ' ', 0, ' ');   \n " +
                    "\tcommit;\n" +
                    "  end if;\n" +
                    "end;\n" +
                    "/";

            System.out.println(sql);
            System.out.println();
            System.out.println();
        }
    }
}
