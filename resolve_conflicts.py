import os

files = [
    "src/main/java/pokemonTextBased/Encounter.java",
    "src/main/java/pokemonTextBased/Game.java",
    "src/main/java/pokemonTextBased/Location.java",
    "src/main/java/pokemonTextBased/SaveData.java",
    "src/main/java/pokemonTextBased/SaveSys.java",
    "src/main/java/pokemonTextBased/User.java"
]

for filepath in files:
    with open(filepath, 'r') as f:
        lines = f.readlines()
    
    out_lines = []
    state = "NORMAL"
    for line in lines:
        if line.startswith("<<<<<<<"):
            state = "IN_HEAD"
            continue
        elif line.startswith("======="):
            state = "IN_THEIRS"
            continue
        elif line.startswith(">>>>>>>"):
            state = "NORMAL"
            continue
            
        if state == "IN_HEAD":
            continue
        else:
            out_lines.append(line)
            
    with open(filepath, 'w') as f:
        f.writelines(out_lines)

print("Conflicts resolved.")
