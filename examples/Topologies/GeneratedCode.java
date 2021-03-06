package examples.Topologies;
import com.tsnsched.core.interface_manager.JSONParser;

import com.tsnsched.core.network.*;

import com.tsnsched.core.nodes.*;

import com.tsnsched.core.components.*;

import com.tsnsched.core.schedule_generator.*;

import com.tsnsched.core.interface_manager.*;


import java.util.*;
import java.io.*;


public class GeneratedCode {
	public void runTestCase(){

		/* 
		* GENERATING DEVICES
		*/
		Device dev0 = new Device(2000, 0, 1000, 72);
		Device dev1 = new Device(2000, 0, 1000, 72);
		Device dev2 = new Device(2000, 0, 1000, 72);
		Device dev3 = new Device(2000, 0, 1000, 72);
		Device dev4 = new Device(2000, 0, 1000, 72);
		Device dev5 = new Device(2000, 0, 1000, 72);
		Device dev6 = new Device(2000, 0, 1000, 72);
		Device dev7 = new Device(2000, 0, 1000, 72);
		Device dev8 = new Device(2000, 0, 1000, 72);
		Device dev9 = new Device(2000, 0, 1000, 72);
		Device dev10 = new Device(2000, 0, 1000, 72);
		Device dev11 = new Device(2000, 0, 1000, 72);
		Device dev12 = new Device(2000, 0, 1000, 72);
		Device dev13 = new Device(2000, 0, 1000, 72);
		Device dev14 = new Device(2000, 0, 1000, 72);
		Device dev15 = new Device(2000, 0, 1000, 72);
		Device dev16 = new Device(2000, 0, 1000, 72);
		Device dev17 = new Device(2000, 0, 1000, 72);
		Device dev18 = new Device(2000, 0, 1000, 72);
		Device dev19 = new Device(2000, 0, 1000, 72);
		Device dev20 = new Device(2000, 0, 1000, 72);
		Device dev21 = new Device(2000, 0, 1000, 72);
		Device dev22 = new Device(2000, 0, 1000, 72);
		Device dev23 = new Device(2000, 0, 1000, 72);
		Device dev24 = new Device(2000, 0, 1000, 72);
		Device dev25 = new Device(2000, 0, 1000, 72);
		Device dev26 = new Device(2000, 0, 1000, 72);
		Device dev27 = new Device(2000, 0, 1000, 72);
		Device dev28 = new Device(2000, 0, 1000, 72);
		Device dev29 = new Device(2000, 0, 1000, 72);
		Device dev30 = new Device(2000, 0, 1000, 72);
		Device dev31 = new Device(2000, 0, 1000, 72);
		Device dev32 = new Device(2000, 0, 1000, 72);
		Device dev33 = new Device(2000, 0, 1000, 72);
		Device dev34 = new Device(2000, 0, 1000, 72);
		Device dev35 = new Device(2000, 0, 1000, 72);
		Device dev36 = new Device(2000, 0, 1000, 72);
		Device dev37 = new Device(2000, 0, 1000, 72);
		Device dev38 = new Device(2000, 0, 1000, 72);
		Device dev39 = new Device(2000, 0, 1000, 72);
		Device dev40 = new Device(2000, 0, 1000, 72);
		Device dev41 = new Device(2000, 0, 1000, 72);
		Device dev42 = new Device(2000, 0, 1000, 72);
		Device dev43 = new Device(2000, 0, 1000, 72);
		Device dev44 = new Device(2000, 0, 1000, 72);
		Device dev45 = new Device(2000, 0, 1000, 72);
		Device dev46 = new Device(2000, 0, 1000, 72);
		Device dev47 = new Device(2000, 0, 1000, 72);
		Device dev48 = new Device(2000, 0, 1000, 72);
		Device dev49 = new Device(2000, 0, 1000, 72);
		Device dev50 = new Device(2000, 0, 1000, 72);
		Device dev51 = new Device(2000, 0, 1000, 72);
		Device dev52 = new Device(2000, 0, 1000, 72);
		Device dev53 = new Device(2000, 0, 1000, 72);
		Device dev54 = new Device(2000, 0, 1000, 72);



		/* 
		* GENERATING SWITCHES
		*/
		TSNSwitch switch0 = new TSNSwitch("switch0",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch1 = new TSNSwitch("switch1",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch2 = new TSNSwitch("switch2",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch3 = new TSNSwitch("switch3",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch4 = new TSNSwitch("switch4",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch5 = new TSNSwitch("switch5",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch6 = new TSNSwitch("switch6",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch7 = new TSNSwitch("switch7",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch8 = new TSNSwitch("switch8",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch9 = new TSNSwitch("switch9",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch10 = new TSNSwitch("switch10",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch11 = new TSNSwitch("switch11",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch12 = new TSNSwitch("switch12",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch13 = new TSNSwitch("switch13",100, 8, 125, 1, 400, 3000);
		TSNSwitch switch14 = new TSNSwitch("switch14",100, 8, 125, 1, 400, 3000);



		/* 
		* GENERATING PORTS
		*/
		Cycle cycle0 = new Cycle(50); 
		switch0.createPort(switch1, cycle0);
		Cycle cycle1 = new Cycle(50); 
		switch1.createPort(switch0, cycle1);
		Cycle cycle2 = new Cycle(50); 
		switch0.createPort(switch2, cycle2);
		Cycle cycle3 = new Cycle(50); 
		switch2.createPort(switch0, cycle3);
		Cycle cycle4 = new Cycle(50); 
		switch0.createPort(switch3, cycle4);
		Cycle cycle5 = new Cycle(50); 
		switch3.createPort(switch0, cycle5);
		Cycle cycle6 = new Cycle(50); 
		switch0.createPort(switch4, cycle6);
		Cycle cycle7 = new Cycle(50); 
		switch4.createPort(switch0, cycle7);
		Cycle cycle8 = new Cycle(50); 
		switch0.createPort(switch5, cycle8);
		Cycle cycle9 = new Cycle(50); 
		switch5.createPort(switch0, cycle9);
		Cycle cycle10 = new Cycle(50); 
		switch0.createPort(switch6, cycle10);
		Cycle cycle11 = new Cycle(50); 
		switch6.createPort(switch0, cycle11);
		Cycle cycle12 = new Cycle(50); 
		switch0.createPort(switch7, cycle12);
		Cycle cycle13 = new Cycle(50); 
		switch7.createPort(switch0, cycle13);
		Cycle cycle14 = new Cycle(50); 
		switch0.createPort(switch8, cycle14);
		Cycle cycle15 = new Cycle(50); 
		switch8.createPort(switch0, cycle15);
		Cycle cycle16 = new Cycle(50); 
		switch0.createPort(switch9, cycle16);
		Cycle cycle17 = new Cycle(50); 
		switch9.createPort(switch0, cycle17);
		Cycle cycle18 = new Cycle(50); 
		switch0.createPort(switch10, cycle18);
		Cycle cycle19 = new Cycle(50); 
		switch10.createPort(switch0, cycle19);
		Cycle cycle20 = new Cycle(50); 
		switch0.createPort(switch11, cycle20);
		Cycle cycle21 = new Cycle(50); 
		switch11.createPort(switch0, cycle21);
		Cycle cycle22 = new Cycle(50); 
		switch0.createPort(switch12, cycle22);
		Cycle cycle23 = new Cycle(50); 
		switch12.createPort(switch0, cycle23);
		Cycle cycle24 = new Cycle(50); 
		switch0.createPort(switch13, cycle24);
		Cycle cycle25 = new Cycle(50); 
		switch13.createPort(switch0, cycle25);
		Cycle cycle26 = new Cycle(50); 
		switch0.createPort(switch14, cycle26);
		Cycle cycle27 = new Cycle(50); 
		switch14.createPort(switch0, cycle27);
		Cycle cycle28 = new Cycle(50); 
		switch1.createPort(switch2, cycle28);
		Cycle cycle29 = new Cycle(50); 
		switch2.createPort(switch1, cycle29);
		Cycle cycle30 = new Cycle(50); 
		switch1.createPort(switch3, cycle30);
		Cycle cycle31 = new Cycle(50); 
		switch3.createPort(switch1, cycle31);
		Cycle cycle32 = new Cycle(50); 
		switch1.createPort(switch4, cycle32);
		Cycle cycle33 = new Cycle(50); 
		switch4.createPort(switch1, cycle33);
		Cycle cycle34 = new Cycle(50); 
		switch1.createPort(switch5, cycle34);
		Cycle cycle35 = new Cycle(50); 
		switch5.createPort(switch1, cycle35);
		Cycle cycle36 = new Cycle(50); 
		switch1.createPort(switch6, cycle36);
		Cycle cycle37 = new Cycle(50); 
		switch6.createPort(switch1, cycle37);
		Cycle cycle38 = new Cycle(50); 
		switch1.createPort(switch7, cycle38);
		Cycle cycle39 = new Cycle(50); 
		switch7.createPort(switch1, cycle39);
		Cycle cycle40 = new Cycle(50); 
		switch1.createPort(switch8, cycle40);
		Cycle cycle41 = new Cycle(50); 
		switch8.createPort(switch1, cycle41);
		Cycle cycle42 = new Cycle(50); 
		switch1.createPort(switch9, cycle42);
		Cycle cycle43 = new Cycle(50); 
		switch9.createPort(switch1, cycle43);
		Cycle cycle44 = new Cycle(50); 
		switch1.createPort(switch10, cycle44);
		Cycle cycle45 = new Cycle(50); 
		switch10.createPort(switch1, cycle45);
		Cycle cycle46 = new Cycle(50); 
		switch1.createPort(switch11, cycle46);
		Cycle cycle47 = new Cycle(50); 
		switch11.createPort(switch1, cycle47);
		Cycle cycle48 = new Cycle(50); 
		switch1.createPort(switch12, cycle48);
		Cycle cycle49 = new Cycle(50); 
		switch12.createPort(switch1, cycle49);
		Cycle cycle50 = new Cycle(50); 
		switch1.createPort(switch13, cycle50);
		Cycle cycle51 = new Cycle(50); 
		switch13.createPort(switch1, cycle51);
		Cycle cycle52 = new Cycle(50); 
		switch1.createPort(switch14, cycle52);
		Cycle cycle53 = new Cycle(50); 
		switch14.createPort(switch1, cycle53);
		Cycle cycle54 = new Cycle(50); 
		switch2.createPort(switch3, cycle54);
		Cycle cycle55 = new Cycle(50); 
		switch3.createPort(switch2, cycle55);
		Cycle cycle56 = new Cycle(50); 
		switch2.createPort(switch4, cycle56);
		Cycle cycle57 = new Cycle(50); 
		switch4.createPort(switch2, cycle57);
		Cycle cycle58 = new Cycle(50); 
		switch2.createPort(switch5, cycle58);
		Cycle cycle59 = new Cycle(50); 
		switch5.createPort(switch2, cycle59);
		Cycle cycle60 = new Cycle(50); 
		switch2.createPort(switch6, cycle60);
		Cycle cycle61 = new Cycle(50); 
		switch6.createPort(switch2, cycle61);
		Cycle cycle62 = new Cycle(50); 
		switch2.createPort(switch7, cycle62);
		Cycle cycle63 = new Cycle(50); 
		switch7.createPort(switch2, cycle63);
		Cycle cycle64 = new Cycle(50); 
		switch2.createPort(switch8, cycle64);
		Cycle cycle65 = new Cycle(50); 
		switch8.createPort(switch2, cycle65);
		Cycle cycle66 = new Cycle(50); 
		switch2.createPort(switch9, cycle66);
		Cycle cycle67 = new Cycle(50); 
		switch9.createPort(switch2, cycle67);
		Cycle cycle68 = new Cycle(50); 
		switch2.createPort(switch10, cycle68);
		Cycle cycle69 = new Cycle(50); 
		switch10.createPort(switch2, cycle69);
		Cycle cycle70 = new Cycle(50); 
		switch2.createPort(switch11, cycle70);
		Cycle cycle71 = new Cycle(50); 
		switch11.createPort(switch2, cycle71);
		Cycle cycle72 = new Cycle(50); 
		switch2.createPort(switch12, cycle72);
		Cycle cycle73 = new Cycle(50); 
		switch12.createPort(switch2, cycle73);
		Cycle cycle74 = new Cycle(50); 
		switch2.createPort(switch13, cycle74);
		Cycle cycle75 = new Cycle(50); 
		switch13.createPort(switch2, cycle75);
		Cycle cycle76 = new Cycle(50); 
		switch2.createPort(switch14, cycle76);
		Cycle cycle77 = new Cycle(50); 
		switch14.createPort(switch2, cycle77);
		Cycle cycle78 = new Cycle(50); 
		switch3.createPort(switch4, cycle78);
		Cycle cycle79 = new Cycle(50); 
		switch4.createPort(switch3, cycle79);
		Cycle cycle80 = new Cycle(50); 
		switch3.createPort(switch5, cycle80);
		Cycle cycle81 = new Cycle(50); 
		switch5.createPort(switch3, cycle81);
		Cycle cycle82 = new Cycle(50); 
		switch3.createPort(switch6, cycle82);
		Cycle cycle83 = new Cycle(50); 
		switch6.createPort(switch3, cycle83);
		Cycle cycle84 = new Cycle(50); 
		switch3.createPort(switch7, cycle84);
		Cycle cycle85 = new Cycle(50); 
		switch7.createPort(switch3, cycle85);
		Cycle cycle86 = new Cycle(50); 
		switch3.createPort(switch8, cycle86);
		Cycle cycle87 = new Cycle(50); 
		switch8.createPort(switch3, cycle87);
		Cycle cycle88 = new Cycle(50); 
		switch3.createPort(switch9, cycle88);
		Cycle cycle89 = new Cycle(50); 
		switch9.createPort(switch3, cycle89);
		Cycle cycle90 = new Cycle(50); 
		switch3.createPort(switch10, cycle90);
		Cycle cycle91 = new Cycle(50); 
		switch10.createPort(switch3, cycle91);
		Cycle cycle92 = new Cycle(50); 
		switch3.createPort(switch11, cycle92);
		Cycle cycle93 = new Cycle(50); 
		switch11.createPort(switch3, cycle93);
		Cycle cycle94 = new Cycle(50); 
		switch3.createPort(switch12, cycle94);
		Cycle cycle95 = new Cycle(50); 
		switch12.createPort(switch3, cycle95);
		Cycle cycle96 = new Cycle(50); 
		switch3.createPort(switch13, cycle96);
		Cycle cycle97 = new Cycle(50); 
		switch13.createPort(switch3, cycle97);
		Cycle cycle98 = new Cycle(50); 
		switch3.createPort(switch14, cycle98);
		Cycle cycle99 = new Cycle(50); 
		switch14.createPort(switch3, cycle99);
		Cycle cycle100 = new Cycle(50); 
		switch4.createPort(switch5, cycle100);
		Cycle cycle101 = new Cycle(50); 
		switch5.createPort(switch4, cycle101);
		Cycle cycle102 = new Cycle(50); 
		switch4.createPort(switch6, cycle102);
		Cycle cycle103 = new Cycle(50); 
		switch6.createPort(switch4, cycle103);
		Cycle cycle104 = new Cycle(50); 
		switch4.createPort(switch7, cycle104);
		Cycle cycle105 = new Cycle(50); 
		switch7.createPort(switch4, cycle105);
		Cycle cycle106 = new Cycle(50); 
		switch4.createPort(switch8, cycle106);
		Cycle cycle107 = new Cycle(50); 
		switch8.createPort(switch4, cycle107);
		Cycle cycle108 = new Cycle(50); 
		switch4.createPort(switch9, cycle108);
		Cycle cycle109 = new Cycle(50); 
		switch9.createPort(switch4, cycle109);
		Cycle cycle110 = new Cycle(50); 
		switch4.createPort(switch10, cycle110);
		Cycle cycle111 = new Cycle(50); 
		switch10.createPort(switch4, cycle111);
		Cycle cycle112 = new Cycle(50); 
		switch4.createPort(switch11, cycle112);
		Cycle cycle113 = new Cycle(50); 
		switch11.createPort(switch4, cycle113);
		Cycle cycle114 = new Cycle(50); 
		switch4.createPort(switch12, cycle114);
		Cycle cycle115 = new Cycle(50); 
		switch12.createPort(switch4, cycle115);
		Cycle cycle116 = new Cycle(50); 
		switch4.createPort(switch13, cycle116);
		Cycle cycle117 = new Cycle(50); 
		switch13.createPort(switch4, cycle117);
		Cycle cycle118 = new Cycle(50); 
		switch4.createPort(switch14, cycle118);
		Cycle cycle119 = new Cycle(50); 
		switch14.createPort(switch4, cycle119);
		Cycle cycle120 = new Cycle(50); 
		switch5.createPort(switch6, cycle120);
		Cycle cycle121 = new Cycle(50); 
		switch6.createPort(switch5, cycle121);
		Cycle cycle122 = new Cycle(50); 
		switch5.createPort(switch7, cycle122);
		Cycle cycle123 = new Cycle(50); 
		switch7.createPort(switch5, cycle123);
		Cycle cycle124 = new Cycle(50); 
		switch5.createPort(switch8, cycle124);
		Cycle cycle125 = new Cycle(50); 
		switch8.createPort(switch5, cycle125);
		Cycle cycle126 = new Cycle(50); 
		switch5.createPort(switch9, cycle126);
		Cycle cycle127 = new Cycle(50); 
		switch9.createPort(switch5, cycle127);
		Cycle cycle128 = new Cycle(50); 
		switch5.createPort(switch10, cycle128);
		Cycle cycle129 = new Cycle(50); 
		switch10.createPort(switch5, cycle129);
		Cycle cycle130 = new Cycle(50); 
		switch5.createPort(switch11, cycle130);
		Cycle cycle131 = new Cycle(50); 
		switch11.createPort(switch5, cycle131);
		Cycle cycle132 = new Cycle(50); 
		switch5.createPort(switch12, cycle132);
		Cycle cycle133 = new Cycle(50); 
		switch12.createPort(switch5, cycle133);
		Cycle cycle134 = new Cycle(50); 
		switch5.createPort(switch13, cycle134);
		Cycle cycle135 = new Cycle(50); 
		switch13.createPort(switch5, cycle135);
		Cycle cycle136 = new Cycle(50); 
		switch5.createPort(switch14, cycle136);
		Cycle cycle137 = new Cycle(50); 
		switch14.createPort(switch5, cycle137);
		Cycle cycle138 = new Cycle(50); 
		switch6.createPort(switch7, cycle138);
		Cycle cycle139 = new Cycle(50); 
		switch7.createPort(switch6, cycle139);
		Cycle cycle140 = new Cycle(50); 
		switch6.createPort(switch8, cycle140);
		Cycle cycle141 = new Cycle(50); 
		switch8.createPort(switch6, cycle141);
		Cycle cycle142 = new Cycle(50); 
		switch6.createPort(switch9, cycle142);
		Cycle cycle143 = new Cycle(50); 
		switch9.createPort(switch6, cycle143);
		Cycle cycle144 = new Cycle(50); 
		switch6.createPort(switch10, cycle144);
		Cycle cycle145 = new Cycle(50); 
		switch10.createPort(switch6, cycle145);
		Cycle cycle146 = new Cycle(50); 
		switch6.createPort(switch11, cycle146);
		Cycle cycle147 = new Cycle(50); 
		switch11.createPort(switch6, cycle147);
		Cycle cycle148 = new Cycle(50); 
		switch6.createPort(switch12, cycle148);
		Cycle cycle149 = new Cycle(50); 
		switch12.createPort(switch6, cycle149);
		Cycle cycle150 = new Cycle(50); 
		switch6.createPort(switch13, cycle150);
		Cycle cycle151 = new Cycle(50); 
		switch13.createPort(switch6, cycle151);
		Cycle cycle152 = new Cycle(50); 
		switch6.createPort(switch14, cycle152);
		Cycle cycle153 = new Cycle(50); 
		switch14.createPort(switch6, cycle153);
		Cycle cycle154 = new Cycle(50); 
		switch7.createPort(switch8, cycle154);
		Cycle cycle155 = new Cycle(50); 
		switch8.createPort(switch7, cycle155);
		Cycle cycle156 = new Cycle(50); 
		switch7.createPort(switch9, cycle156);
		Cycle cycle157 = new Cycle(50); 
		switch9.createPort(switch7, cycle157);
		Cycle cycle158 = new Cycle(50); 
		switch7.createPort(switch10, cycle158);
		Cycle cycle159 = new Cycle(50); 
		switch10.createPort(switch7, cycle159);
		Cycle cycle160 = new Cycle(50); 
		switch7.createPort(switch11, cycle160);
		Cycle cycle161 = new Cycle(50); 
		switch11.createPort(switch7, cycle161);
		Cycle cycle162 = new Cycle(50); 
		switch7.createPort(switch12, cycle162);
		Cycle cycle163 = new Cycle(50); 
		switch12.createPort(switch7, cycle163);
		Cycle cycle164 = new Cycle(50); 
		switch7.createPort(switch13, cycle164);
		Cycle cycle165 = new Cycle(50); 
		switch13.createPort(switch7, cycle165);
		Cycle cycle166 = new Cycle(50); 
		switch7.createPort(switch14, cycle166);
		Cycle cycle167 = new Cycle(50); 
		switch14.createPort(switch7, cycle167);
		Cycle cycle168 = new Cycle(50); 
		switch8.createPort(switch9, cycle168);
		Cycle cycle169 = new Cycle(50); 
		switch9.createPort(switch8, cycle169);
		Cycle cycle170 = new Cycle(50); 
		switch8.createPort(switch10, cycle170);
		Cycle cycle171 = new Cycle(50); 
		switch10.createPort(switch8, cycle171);
		Cycle cycle172 = new Cycle(50); 
		switch8.createPort(switch11, cycle172);
		Cycle cycle173 = new Cycle(50); 
		switch11.createPort(switch8, cycle173);
		Cycle cycle174 = new Cycle(50); 
		switch8.createPort(switch12, cycle174);
		Cycle cycle175 = new Cycle(50); 
		switch12.createPort(switch8, cycle175);
		Cycle cycle176 = new Cycle(50); 
		switch8.createPort(switch13, cycle176);
		Cycle cycle177 = new Cycle(50); 
		switch13.createPort(switch8, cycle177);
		Cycle cycle178 = new Cycle(50); 
		switch8.createPort(switch14, cycle178);
		Cycle cycle179 = new Cycle(50); 
		switch14.createPort(switch8, cycle179);
		Cycle cycle180 = new Cycle(50); 
		switch9.createPort(switch10, cycle180);
		Cycle cycle181 = new Cycle(50); 
		switch10.createPort(switch9, cycle181);
		Cycle cycle182 = new Cycle(50); 
		switch9.createPort(switch11, cycle182);
		Cycle cycle183 = new Cycle(50); 
		switch11.createPort(switch9, cycle183);
		Cycle cycle184 = new Cycle(50); 
		switch9.createPort(switch12, cycle184);
		Cycle cycle185 = new Cycle(50); 
		switch12.createPort(switch9, cycle185);
		Cycle cycle186 = new Cycle(50); 
		switch9.createPort(switch13, cycle186);
		Cycle cycle187 = new Cycle(50); 
		switch13.createPort(switch9, cycle187);
		Cycle cycle188 = new Cycle(50); 
		switch9.createPort(switch14, cycle188);
		Cycle cycle189 = new Cycle(50); 
		switch14.createPort(switch9, cycle189);
		Cycle cycle190 = new Cycle(50); 
		switch10.createPort(switch11, cycle190);
		Cycle cycle191 = new Cycle(50); 
		switch11.createPort(switch10, cycle191);
		Cycle cycle192 = new Cycle(50); 
		switch10.createPort(switch12, cycle192);
		Cycle cycle193 = new Cycle(50); 
		switch12.createPort(switch10, cycle193);
		Cycle cycle194 = new Cycle(50); 
		switch10.createPort(switch13, cycle194);
		Cycle cycle195 = new Cycle(50); 
		switch13.createPort(switch10, cycle195);
		Cycle cycle196 = new Cycle(50); 
		switch10.createPort(switch14, cycle196);
		Cycle cycle197 = new Cycle(50); 
		switch14.createPort(switch10, cycle197);
		Cycle cycle198 = new Cycle(50); 
		switch11.createPort(switch12, cycle198);
		Cycle cycle199 = new Cycle(50); 
		switch12.createPort(switch11, cycle199);
		Cycle cycle200 = new Cycle(50); 
		switch11.createPort(switch13, cycle200);
		Cycle cycle201 = new Cycle(50); 
		switch13.createPort(switch11, cycle201);
		Cycle cycle202 = new Cycle(50); 
		switch11.createPort(switch14, cycle202);
		Cycle cycle203 = new Cycle(50); 
		switch14.createPort(switch11, cycle203);
		Cycle cycle204 = new Cycle(50); 
		switch12.createPort(switch13, cycle204);
		Cycle cycle205 = new Cycle(50); 
		switch13.createPort(switch12, cycle205);
		Cycle cycle206 = new Cycle(50); 
		switch12.createPort(switch14, cycle206);
		Cycle cycle207 = new Cycle(50); 
		switch14.createPort(switch12, cycle207);
		Cycle cycle208 = new Cycle(50); 
		switch13.createPort(switch14, cycle208);
		Cycle cycle209 = new Cycle(50); 
		switch14.createPort(switch13, cycle209);

		/* 
		* LINKING SWITCHES TO DEVICES 
		*/
		Cycle cycle210 = new Cycle(50); 
		switch0.createPort(dev0, cycle210);
		Cycle cycle211 = new Cycle(50); 
		switch0.createPort(dev1, cycle211);
		Cycle cycle212 = new Cycle(50); 
		switch0.createPort(dev2, cycle212);
		Cycle cycle213 = new Cycle(50); 
		switch1.createPort(dev3, cycle213);
		Cycle cycle214 = new Cycle(50); 
		switch1.createPort(dev4, cycle214);
		Cycle cycle215 = new Cycle(50); 
		switch1.createPort(dev5, cycle215);
		Cycle cycle216 = new Cycle(50); 
		switch2.createPort(dev6, cycle216);
		Cycle cycle217 = new Cycle(50); 
		switch2.createPort(dev7, cycle217);
		Cycle cycle218 = new Cycle(50); 
		switch2.createPort(dev8, cycle218);
		Cycle cycle219 = new Cycle(50); 
		switch3.createPort(dev9, cycle219);
		Cycle cycle220 = new Cycle(50); 
		switch3.createPort(dev10, cycle220);
		Cycle cycle221 = new Cycle(50); 
		switch3.createPort(dev11, cycle221);
		Cycle cycle222 = new Cycle(50); 
		switch4.createPort(dev12, cycle222);
		Cycle cycle223 = new Cycle(50); 
		switch4.createPort(dev13, cycle223);
		Cycle cycle224 = new Cycle(50); 
		switch4.createPort(dev14, cycle224);
		Cycle cycle225 = new Cycle(50); 
		switch5.createPort(dev15, cycle225);
		Cycle cycle226 = new Cycle(50); 
		switch5.createPort(dev16, cycle226);
		Cycle cycle227 = new Cycle(50); 
		switch5.createPort(dev17, cycle227);
		Cycle cycle228 = new Cycle(50); 
		switch6.createPort(dev18, cycle228);
		Cycle cycle229 = new Cycle(50); 
		switch6.createPort(dev19, cycle229);
		Cycle cycle230 = new Cycle(50); 
		switch6.createPort(dev20, cycle230);
		Cycle cycle231 = new Cycle(50); 
		switch7.createPort(dev21, cycle231);
		Cycle cycle232 = new Cycle(50); 
		switch7.createPort(dev22, cycle232);
		Cycle cycle233 = new Cycle(50); 
		switch7.createPort(dev23, cycle233);
		Cycle cycle234 = new Cycle(50); 
		switch8.createPort(dev24, cycle234);
		Cycle cycle235 = new Cycle(50); 
		switch8.createPort(dev25, cycle235);
		Cycle cycle236 = new Cycle(50); 
		switch8.createPort(dev26, cycle236);
		Cycle cycle237 = new Cycle(50); 
		switch9.createPort(dev27, cycle237);
		Cycle cycle238 = new Cycle(50); 
		switch9.createPort(dev28, cycle238);
		Cycle cycle239 = new Cycle(50); 
		switch9.createPort(dev29, cycle239);
		Cycle cycle240 = new Cycle(50); 
		switch10.createPort(dev30, cycle240);
		Cycle cycle241 = new Cycle(50); 
		switch10.createPort(dev31, cycle241);
		Cycle cycle242 = new Cycle(50); 
		switch10.createPort(dev32, cycle242);
		Cycle cycle243 = new Cycle(50); 
		switch11.createPort(dev33, cycle243);
		Cycle cycle244 = new Cycle(50); 
		switch11.createPort(dev34, cycle244);
		Cycle cycle245 = new Cycle(50); 
		switch11.createPort(dev35, cycle245);
		Cycle cycle246 = new Cycle(50); 
		switch12.createPort(dev36, cycle246);
		Cycle cycle247 = new Cycle(50); 
		switch12.createPort(dev37, cycle247);
		Cycle cycle248 = new Cycle(50); 
		switch12.createPort(dev38, cycle248);
		Cycle cycle249 = new Cycle(50); 
		switch13.createPort(dev39, cycle249);
		Cycle cycle250 = new Cycle(50); 
		switch13.createPort(dev40, cycle250);
		Cycle cycle251 = new Cycle(50); 
		switch13.createPort(dev41, cycle251);
		Cycle cycle252 = new Cycle(50); 
		switch14.createPort(dev42, cycle252);
		Cycle cycle253 = new Cycle(50); 
		switch14.createPort(dev43, cycle253);
		Cycle cycle254 = new Cycle(50); 
		switch14.createPort(dev44, cycle254);
		Cycle cycle255 = new Cycle(50); 
		switch14.createPort(dev45, cycle255);
		Cycle cycle256 = new Cycle(50); 
		switch14.createPort(dev46, cycle256);
		Cycle cycle257 = new Cycle(50); 
		switch14.createPort(dev47, cycle257);
		Cycle cycle258 = new Cycle(50); 
		switch14.createPort(dev48, cycle258);
		Cycle cycle259 = new Cycle(50); 
		switch14.createPort(dev49, cycle259);
		Cycle cycle260 = new Cycle(50); 
		switch14.createPort(dev50, cycle260);
		Cycle cycle261 = new Cycle(50); 
		switch14.createPort(dev51, cycle261);
		Cycle cycle262 = new Cycle(50); 
		switch14.createPort(dev52, cycle262);
		Cycle cycle263 = new Cycle(50); 
		switch14.createPort(dev53, cycle263);
		Cycle cycle264 = new Cycle(50); 
		switch14.createPort(dev54, cycle264);


		/* 
		* GENERATING FLOWS
		*/
		LinkedList<PathNode> nodeList;

		Flow flow0 = new Flow(Flow.PUBLISH_SUBSCRIBE);
		PathTree pathTree0 = new PathTree();
		PathNode pathNode0;
		pathNode0 = pathTree0.addRoot(dev29);
		pathNode0 = pathNode0.addChild(switch9);
		nodeList = new LinkedList<PathNode>();
		nodeList.add(pathNode0);
		nodeList.add(nodeList.getFirst().addChild(switch1));
		nodeList.add(nodeList.removeFirst().addChild(switch6));
		nodeList.getFirst().addChild(dev3);
		nodeList.getFirst().addChild(dev4);
		nodeList.removeFirst();
		nodeList.getFirst().addChild(dev18);
		nodeList.getFirst().addChild(dev19);
		nodeList.removeFirst();
		flow0.setPathTree(pathTree0);

		Flow flow1 = new Flow(Flow.PUBLISH_SUBSCRIBE);
		PathTree pathTree1 = new PathTree();
		PathNode pathNode1;
		pathNode1 = pathTree1.addRoot(dev2);
		pathNode1 = pathNode1.addChild(switch0);
		nodeList = new LinkedList<PathNode>();
		nodeList.add(pathNode1);
		nodeList.add(nodeList.getFirst().addChild(switch4));
		nodeList.add(nodeList.removeFirst().addChild(switch13));
		nodeList.getFirst().addChild(dev12);
		nodeList.getFirst().addChild(dev13);
		nodeList.removeFirst();
		nodeList.getFirst().addChild(dev39);
		nodeList.getFirst().addChild(dev40);
		nodeList.removeFirst();
		flow1.setPathTree(pathTree1);


		/* 
		* GENERATING THE NETWORK
		*/
		Network net = new Network();
		net.addDevice(dev0);
		net.addDevice(dev1);
		net.addDevice(dev2);
		net.addDevice(dev3);
		net.addDevice(dev4);
		net.addDevice(dev5);
		net.addDevice(dev6);
		net.addDevice(dev7);
		net.addDevice(dev8);
		net.addDevice(dev9);
		net.addDevice(dev10);
		net.addDevice(dev11);
		net.addDevice(dev12);
		net.addDevice(dev13);
		net.addDevice(dev14);
		net.addDevice(dev15);
		net.addDevice(dev16);
		net.addDevice(dev17);
		net.addDevice(dev18);
		net.addDevice(dev19);
		net.addDevice(dev20);
		net.addDevice(dev21);
		net.addDevice(dev22);
		net.addDevice(dev23);
		net.addDevice(dev24);
		net.addDevice(dev25);
		net.addDevice(dev26);
		net.addDevice(dev27);
		net.addDevice(dev28);
		net.addDevice(dev29);
		net.addDevice(dev30);
		net.addDevice(dev31);
		net.addDevice(dev32);
		net.addDevice(dev33);
		net.addDevice(dev34);
		net.addDevice(dev35);
		net.addDevice(dev36);
		net.addDevice(dev37);
		net.addDevice(dev38);
		net.addDevice(dev39);
		net.addDevice(dev40);
		net.addDevice(dev41);
		net.addDevice(dev42);
		net.addDevice(dev43);
		net.addDevice(dev44);
		net.addDevice(dev45);
		net.addDevice(dev46);
		net.addDevice(dev47);
		net.addDevice(dev48);
		net.addDevice(dev49);
		net.addDevice(dev50);
		net.addDevice(dev51);
		net.addDevice(dev52);
		net.addDevice(dev53);
		net.addDevice(dev54);
		net.addSwitch(switch0);
		net.addSwitch(switch1);
		net.addSwitch(switch2);
		net.addSwitch(switch3);
		net.addSwitch(switch4);
		net.addSwitch(switch5);
		net.addSwitch(switch6);
		net.addSwitch(switch7);
		net.addSwitch(switch8);
		net.addSwitch(switch9);
		net.addSwitch(switch10);
		net.addSwitch(switch11);
		net.addSwitch(switch12);
		net.addSwitch(switch13);
		net.addSwitch(switch14);
		net.addFlow(flow0);
		net.addFlow(flow1);


		ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
		scheduleGenerator.generateSchedule(net);

	}
}
