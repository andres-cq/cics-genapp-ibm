# AGENTS.md

This file provides guidance to agents when working with code in this repository.

## Build Process

**CRITICAL**: JCL members MUST be customized before use:
1. Edit `base/exec/cust1.rexx` with environment-specific values (DB2 subsystem, HLQs, etc.)
2. Run `EXEC 'userid.GENAPP.EXEC(CUST1)'` from TSO - this prefixes customized members with `@`
3. Submit jobs with `@` prefix only (e.g., `@COBOL`, not `COBOL`)
4. Customization can be re-run multiple times - it overwrites previous `@` members

## Build Order (Must Follow Sequence)

1. `@ADEF121` - Create/load VSAM files (KSDSCUST, KSDSPOLY)
2. `@ASMMAP` - Assemble BMS maps (returns RC=4, this is expected)
3. `@CDEF121` - Add resource definitions to CSD (creates GENALIST group)
4. `@COBOL` - Compile all COBOL programs to userid.GENAPP.LOAD
5. `@DB2CRE` - Create DB2 database, tables, indexes, and load data
6. `@DB2BIND` - Bind application to DB2 objects
7. Optional: Create coupling facility structures for named counter/TSQ servers
8. Optional: `@SAMPNCS` - Start named counter server (long-running job)
9. Optional: `@SAMPTSQ` - Start TSQ server (long-running job)
10. Update CICS startup JCL: Add `GRPLIST=(DFHLIST,GENALIST)`, `DB2CONN=YES`, `NCPLDFT=GENA`
11. Add userid.GENAPP.LOAD to DFHRPL concatenation

## Testing

**CRITICAL**: Run `LGSE` transaction FIRST before any other transactions - it initializes control tables, clears TSQ, and resets named counters to match restored DB2 data.

Test transactions: `SSC1` (customer menu), `SSP1`-`SSP4` (policy menus)
- Sample customer numbers: 1-10
- Sample policy numbers: 1-10 (see base/Reference.md for policy/customer mappings)

## COBOL Compilation

- Compiler options in JCL: `NODYNAM RENT APOST CICS CODEPAGE(<DB2CCSID>)`
- All programs use CICS translator and DB2 precompiler
- DBRM output goes to userid.GENAPP.DBRMLIB
- Linked with DFHEILIC stub, DB2 libraries, and Language Environment
- linkparm.txt in base/src/ contains additional link parameters

## Code Patterns

- Programs follow naming: `LG` prefix + operation (A=Add, I=Inquire, U=Update, D=Delete) + target (CUS=Customer, POL=Policy, DB=DB2, VS=VSAM)
- Business logic programs (e.g., LGACUS01) call data access programs (LGACDB01 for DB2, LGACVS01 for VSAM)
- Two-phase commit used: DB2 + VSAM updates in same unit of work
- COMMAREA structure defined in lgcmarea.cpy - all programs expect this
- Named counter GENACUSTNUM allocates customer numbers (when configured)
- TSQ GENACNTL stores low/high customer values for validation

## File Organization

- `base/cntl/` - JCL jobs (customize with CUST1 REXX)
- `base/src/` - COBOL source and copybooks (.cbl, .cpy, .bms)
- `base/exec/` - REXX customization scripts
- `base/data/` - Sample data files (ksdscust.txt, ksdspoly.txt)
- `base/wsim/` - Workload Simulator scripts (required even if not using WSIM)

## Non-Standard Behaviors

- BMS map assembly returns RC=4 - this is normal, not an error
- CUST1 REXX uses ISPF EDIT with MAC1 macro for substitution - runs in TSO/ISPF only
- Named counter and TSQ servers are optional but recommended for multi-region setups
- DB2 database name (DB2DBID) can be any valid DB2 name, not fixed to GENASA1