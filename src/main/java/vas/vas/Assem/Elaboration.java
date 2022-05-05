package vas.vas.Assem;

import vas.vas.Support.LngDefines;
import vas.vas.Support.Others;
import vas.vas.Support.pop_up;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Elaboration {
	Read r = new Read();
	pop_up er = new pop_up();
	public ArrayList<instructions> inst = new ArrayList<>();
	String[] RegList;
	String[] ModList;
	public void setInstructions() throws IOException {
		String instructionSet;
		if(Others.Instruction_Mode == Others.developer_mode){
			if(Others.path_developer.equals("")){
				Others.developer_path();
			}
			if(!Others.developer_file_exist()){
				pop_up.generating_file();
				Others.generate_developer_file();
			}
			instructionSet = r.readfilePass(Others.path_developer);
		}
		else {
			instructionSet = new BufferedReader(
					new InputStreamReader(getFileFromResourceAsStream("vas/vas/others/instruction.txt"), StandardCharsets.UTF_8))
					.lines()
					.collect(Collectors.joining("\n"));
		}

		String[] atemp = instructionSet.split("___");
		RegList = r.linedivision(atemp[0]);
		ModList = r.linedivision(atemp[1]);
		String[] linesSet = r.linedivision(atemp[2]);
		for (int i = 1; i < linesSet.length; i++) {
			inst.add(new instructions(linesSet[i].substring(0, linesSet[i].indexOf(" ")),
					linesSet[i].substring(linesSet[i].indexOf(" ") + 1), RegList, ModList));
		}
	}
	public static InputStream getFileFromResourceAsStream(String fileName) {

		// The class loader that loaded the class
		ClassLoader classLoader = Elaboration.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);

		//Di base è impossibile che questo errore si presenti, ma lo lascio comunque
		if (inputStream == null) {
			throw new IllegalArgumentException("Istruzioni non trovate!");
		} else {
			return inputStream;
		}

	}

	public String translation(String input) {
		ArrayList<codeline> code = new ArrayList<codeline>();
		String temp;
		String trad = "";
		int tempFactorPos = 0;
		int WordsCounter = 0;
		int tempNB = 0;
		String[] atemp = input.split("\n");
		for (int i = 0; i < atemp.length; i++) {
			code.add(new codeline(atemp[i], RegList, ModList));
		}
		for (int i = 0; i < code.size(); i++) {
			code.get(i).Position = WordsCounter;
			if(code.get(i).isORG) WordsCounter+=code.get(i).ORG;
			/* RAM portion occupied */
			else {
			for (int c = 0; c < inst.size(); c++) {
				if (code.get(i).Key.equals(inst.get(c).Key)
						&& code.get(i).ContainedMod.equals(inst.get(c).ContainedMod)) {
					if (code.get(i).FirstIsNum) {
						if (code.get(i).FirstNB <= inst.get(c).FirstNB) {
							if (code.get(i).SecondIsNum) {
								if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
									// trad+=inst.get(c).MachineCode+"\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN)+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
									WordsCounter += 1 + code.get(i).FirstNB + code.get(i).SecondNB;
								}
							} else {
								if (inst.get(c).SecondIsNum) {
									// Boolean isLabel=false;
									for (int l = 0; l < code.size(); l++) {
										if (code.get(i).SecondFactor.equals(code.get(l).label)) {
											// trad+=inst.get(c).MachineCode+"\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN)+DeHex(1,code.get(l).Position);
											WordsCounter += 2 + code.get(i).FirstNB;
											// isLabel=true;
										} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
												&& code.get(i).GetNumberOfBytes(
														code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
											WordsCounter += 1 + code.get(i).GetNumberOfBytes(code.get(l).Cost.getn())
													+ code.get(i).FirstNB;
										}
									}
									// if(!isLabel)infoBox("You digited "+code.get(i).SecondFactor+" instead of a
									// number","Attention please");
								} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
									// trad+=inst.get(c).MachineCode+"\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN);

									WordsCounter += 1 + code.get(i).FirstNB;
								}
							}
						}
					} else {
						if (inst.get(c).FirstIsNum) {
							Boolean isLabel = false;
							for (int l = 0; l < code.size(); l++) {
								if (code.get(i).FirstFactor.equals(code.get(l).label)) {
									isLabel = true;
									tempFactorPos = code.get(l).Position;
									tempNB = 1;
								} else if (code.get(i).FirstFactor.equals(code.get(l).Cost.gets()) && code.get(i)
										.GetNumberOfBytes(code.get(l).Cost.getn()) <= inst.get(c).FirstNB) {
									isLabel = true;
									tempFactorPos = code.get(l).Cost.getn();
									tempNB = code.get(l).GetNumberOfBytes(code.get(l).Cost.getn());
								}
							}
							if (isLabel) {
								if (code.get(i).SecondIsNum) {
									if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
										// trad+=inst.get(c).MachineCode+"\n"+DeHex(1,tempFactorPos)+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
										WordsCounter += 1 + tempNB + code.get(i).SecondNB;
									}
								} else {
									if (inst.get(c).SecondIsNum) {
										// Boolean isLabell=false;
										for (int l = 0; l < code.size(); l++) {
											if (code.get(i).SecondFactor.equals(code.get(l).label)) {
												// trad+=inst.get(c).MachineCode+"\n"+DeHex(1,tempFactorPos)+DeHex(1,code.get(l).Position);

												WordsCounter += 2 + tempNB;
												// isLabell=true;
											} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
													&& code.get(i).GetNumberOfBytes(
															code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
												WordsCounter += 1 + tempNB
														+ code.get(l).GetNumberOfBytes(code.get(l).Cost.getn());
											}
										}
										// if(!isLabell)infoBox("You digited "+code.get(i).SecondFactor+" instead of a
										// number","Attention please");
									} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
										// trad+=inst.get(c).MachineCode+"\n"+DeHex(1,tempFactorPos);

										WordsCounter += 1 + tempNB;
									}
								}
							}
						} else if (code.get(i).FirstFactor.equals(inst.get(c).FirstFactor)) {
							if (code.get(i).SecondIsNum) {
								if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
									// trad+=inst.get(c).MachineCode+"\n"+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
									WordsCounter += 1 + code.get(i).SecondNB;
								}
							} else {
								if (inst.get(c).SecondIsNum) {
									// Boolean isLabel=false;
									for (int l = 0; l < code.size(); l++) {
										if (code.get(i).SecondFactor.equals(code.get(l).label)) {
											// trad+=inst.get(c).MachineCode+"\n"+DeHex(1,code.get(l).Position);
											WordsCounter += 2;
											// isLabel=true;
										} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
												&& code.get(i).GetNumberOfBytes(
														code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
											WordsCounter += 1 + code.get(l).GetNumberOfBytes(code.get(l).Cost.getn());
										}
									}
								} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
									// trad+=inst.get(c).MachineCode+"\n";
									WordsCounter++;
								}
							}
						}
					}
				}
			}
			}
		}
		for (int i = 0; i < code.size(); i++) {
			temp = trad;
			if(code.get(i).isORG )
			for(int k=0;k<(code.get(i).ORG-code.get(i).Position-1);k++) {
				trad+="00\n";
			}else {
			for (int c = 0; c < inst.size(); c++) {
				if (code.get(i).Key.equals(inst.get(c).Key)
						&& code.get(i).ContainedMod.equals(inst.get(c).ContainedMod)) {
					if (code.get(i).FirstIsNum) {
						if (code.get(i).FirstNB <= inst.get(c).FirstNB) {
							if (code.get(i).SecondIsNum) {
								if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
									trad += inst.get(c).MachineCode + "\n"
											+ DeHex(code.get(i).FirstNB, code.get(i).FirstN)
											+ DeHex(code.get(i).SecondNB, code.get(i).SecondN);
								}
							} else {
								if (inst.get(c).SecondIsNum) {
									// Boolean isLabel=false;
									for (int l = 0; l < code.size(); l++) {
										if (code.get(i).SecondFactor.equals(code.get(l).label)) {
											trad += inst.get(c).MachineCode + "\n"
													+ DeHex(code.get(i).FirstNB, code.get(i).FirstN)
													+ DeHex(1, code.get(l).Position);
											// isLabel=true;
										} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
												&& code.get(i).GetNumberOfBytes(
														code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
											trad += inst.get(c).MachineCode + "\n"
													+ DeHex(code.get(i).FirstNB, code.get(i).FirstN)
													+ DeHex(code.get(l).GetNumberOfBytes(code.get(l).Cost.getn()),
															code.get(l).Cost.getn());
										}
									}
									// if(!isLabel)infoBox("You digited "+code.get(i).SecondFactor+" instead of a
									// number","Attention please");
								} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
									trad += inst.get(c).MachineCode + "\n"
											+ DeHex(code.get(i).FirstNB, code.get(i).FirstN);
								}
							}
						}
					} else {
						if (inst.get(c).FirstIsNum) {
							Boolean isLabel = false;
							for (int l = 0; l < code.size(); l++) {
								if (code.get(i).FirstFactor.equals(code.get(l).label)) {
									isLabel = true;
									tempFactorPos = code.get(l).Position;
									tempNB = 1;
								} else if (code.get(i).FirstFactor.equals(code.get(l).Cost.gets()) && code.get(i)
										.GetNumberOfBytes(code.get(l).Cost.getn()) <= inst.get(c).FirstNB) {
									isLabel = true;
									tempFactorPos = code.get(l).Cost.getn();
									tempNB = code.get(l).GetNumberOfBytes(code.get(l).Cost.getn());
								}
							}
							if (isLabel) {
								if (code.get(i).SecondIsNum) {
									if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
										trad += inst.get(c).MachineCode + "\n" + DeHex(tempNB, tempFactorPos)
												+ DeHex(code.get(i).SecondNB, code.get(i).SecondN);
									}
								} else {
									if (inst.get(c).SecondIsNum) {
										// Boolean isLabell=false;
										for (int l = 0; l < code.size(); l++) {
											if (code.get(i).SecondFactor.equals(code.get(l).label)) {
												trad += inst.get(c).MachineCode + "\n" + DeHex(tempNB, tempFactorPos)
														+ DeHex(1, code.get(l).Position);
												// isLabell=true;
											} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
													&& code.get(i).GetNumberOfBytes(
															code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
												trad += inst.get(c).MachineCode + "\n" + DeHex(tempNB, tempFactorPos)
														+ DeHex(code.get(l).GetNumberOfBytes(code.get(l).Cost.getn()),
																code.get(l).Cost.getn());
											}
										}
										// if(!isLabell)infoBox("You digited "+code.get(i).SecondFactor+" instead of a
										// number","Attention please");
									} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
										trad += inst.get(c).MachineCode + "\n" + DeHex(tempNB, tempFactorPos);
									}
								}
							}
						} else if (code.get(i).FirstFactor.equals(inst.get(c).FirstFactor)) {
							if (code.get(i).SecondIsNum) {
								if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
									trad += inst.get(c).MachineCode + "\n"
											+ DeHex(code.get(i).SecondNB, code.get(i).SecondN);
								}
							} else {
								if (inst.get(c).SecondIsNum) {
									// Boolean isLabel=false;
									for (int l = 0; l < code.size(); l++) {
										if (code.get(i).SecondFactor.equals(code.get(l).label)) {
											trad += inst.get(c).MachineCode + "\n" + DeHex(1, code.get(l).Position);
											// isLabel=true;
										} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
												&& code.get(i).GetNumberOfBytes(
														code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
											trad += inst.get(c).MachineCode + "\n"
													+ DeHex(code.get(l).GetNumberOfBytes(code.get(l).Cost.getn()),
															code.get(l).Cost.getn());
										}
									}
								} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
									trad += inst.get(c).MachineCode + "\n";
								}
							}
						}
					}
				}
			}
			if (temp.equals(trad) && !code.get(i).Key.equals("")) {
				er.error_load(LngDefines.LNG_Error_in_line_using + (i + 1) + LngDefines.LNG_Error_in_line_2_using);
			}
		}
		}
		er.error_load(LngDefines.LNG_Conversion_complete_using);
		return trad;
	}

	private String DeHex(int NB, int num) {
		String ByS = Integer.toHexString(num);
		String res = "";
		while (ByS.length() / 2 <= NB) {
			ByS = "0" + ByS;
		}
		for (int i = 0; i < NB; i++) {
			res += ByS.substring(ByS.length() - 2) + "\n";
			;
			ByS = ByS.substring(0, ByS.length() - 2);
		}
		res = res.toUpperCase();
		return res;
	}

}
