export interface IVkaVka {
    id?: number;
    vnr?: string;
    versArt?: string;
    prioritaet?: string;
    bearbDat?: string;
    bearbUhr?: string;
    status?: string;
    rd?: string;
    ges?: string;
    bza?: string;
    tarif?: string;
    tarifUnr1?: string;
    tarifUnr2?: string;
    mkt1?: string;
    bewegSchl?: string;
    wirkungDat?: string;
    antAufnDat?: string;
    antEingDat?: string;
    anzahlStruk?: string;
    btg?: number;
}

export class VkaVka implements IVkaVka {
    constructor(
        public id?: number,
        public vnr?: string,
        public versArt?: string,
        public prioritaet?: string,
        public bearbDat?: string,
        public bearbUhr?: string,
        public status?: string,
        public rd?: string,
        public ges?: string,
        public bza?: string,
        public tarif?: string,
        public tarifUnr1?: string,
        public tarifUnr2?: string,
        public mkt1?: string,
        public bewegSchl?: string,
        public wirkungDat?: string,
        public antAufnDat?: string,
        public antEingDat?: string,
        public anzahlStruk?: string,
        public btg?: number
    ) {}
}
