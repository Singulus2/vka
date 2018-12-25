import { element, by, ElementFinder } from 'protractor';

export class VkaComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-vka-vka div table .btn-danger'));
    title = element.all(by.css('jhi-vka-vka div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class VkaUpdatePage {
    pageTitle = element(by.id('jhi-vka-vka-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    vnrInput = element(by.id('field_vnr'));
    versArtInput = element(by.id('field_versArt'));
    prioritaetInput = element(by.id('field_prioritaet'));
    bearbDatInput = element(by.id('field_bearbDat'));
    bearbUhrInput = element(by.id('field_bearbUhr'));
    statusInput = element(by.id('field_status'));
    rdInput = element(by.id('field_rd'));
    gesInput = element(by.id('field_ges'));
    bzaInput = element(by.id('field_bza'));
    tarifInput = element(by.id('field_tarif'));
    tarifUnr1Input = element(by.id('field_tarifUnr1'));
    tarifUnr2Input = element(by.id('field_tarifUnr2'));
    mkt1Input = element(by.id('field_mkt1'));
    bewegSchlInput = element(by.id('field_bewegSchl'));
    wirkungDatInput = element(by.id('field_wirkungDat'));
    antAufnDatInput = element(by.id('field_antAufnDat'));
    antEingDatInput = element(by.id('field_antEingDat'));
    anzahlStrukInput = element(by.id('field_anzahlStruk'));
    btgInput = element(by.id('field_btg'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setVnrInput(vnr) {
        await this.vnrInput.sendKeys(vnr);
    }

    async getVnrInput() {
        return this.vnrInput.getAttribute('value');
    }

    async setVersArtInput(versArt) {
        await this.versArtInput.sendKeys(versArt);
    }

    async getVersArtInput() {
        return this.versArtInput.getAttribute('value');
    }

    async setPrioritaetInput(prioritaet) {
        await this.prioritaetInput.sendKeys(prioritaet);
    }

    async getPrioritaetInput() {
        return this.prioritaetInput.getAttribute('value');
    }

    async setBearbDatInput(bearbDat) {
        await this.bearbDatInput.sendKeys(bearbDat);
    }

    async getBearbDatInput() {
        return this.bearbDatInput.getAttribute('value');
    }

    async setBearbUhrInput(bearbUhr) {
        await this.bearbUhrInput.sendKeys(bearbUhr);
    }

    async getBearbUhrInput() {
        return this.bearbUhrInput.getAttribute('value');
    }

    async setStatusInput(status) {
        await this.statusInput.sendKeys(status);
    }

    async getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    async setRdInput(rd) {
        await this.rdInput.sendKeys(rd);
    }

    async getRdInput() {
        return this.rdInput.getAttribute('value');
    }

    async setGesInput(ges) {
        await this.gesInput.sendKeys(ges);
    }

    async getGesInput() {
        return this.gesInput.getAttribute('value');
    }

    async setBzaInput(bza) {
        await this.bzaInput.sendKeys(bza);
    }

    async getBzaInput() {
        return this.bzaInput.getAttribute('value');
    }

    async setTarifInput(tarif) {
        await this.tarifInput.sendKeys(tarif);
    }

    async getTarifInput() {
        return this.tarifInput.getAttribute('value');
    }

    async setTarifUnr1Input(tarifUnr1) {
        await this.tarifUnr1Input.sendKeys(tarifUnr1);
    }

    async getTarifUnr1Input() {
        return this.tarifUnr1Input.getAttribute('value');
    }

    async setTarifUnr2Input(tarifUnr2) {
        await this.tarifUnr2Input.sendKeys(tarifUnr2);
    }

    async getTarifUnr2Input() {
        return this.tarifUnr2Input.getAttribute('value');
    }

    async setMkt1Input(mkt1) {
        await this.mkt1Input.sendKeys(mkt1);
    }

    async getMkt1Input() {
        return this.mkt1Input.getAttribute('value');
    }

    async setBewegSchlInput(bewegSchl) {
        await this.bewegSchlInput.sendKeys(bewegSchl);
    }

    async getBewegSchlInput() {
        return this.bewegSchlInput.getAttribute('value');
    }

    async setWirkungDatInput(wirkungDat) {
        await this.wirkungDatInput.sendKeys(wirkungDat);
    }

    async getWirkungDatInput() {
        return this.wirkungDatInput.getAttribute('value');
    }

    async setAntAufnDatInput(antAufnDat) {
        await this.antAufnDatInput.sendKeys(antAufnDat);
    }

    async getAntAufnDatInput() {
        return this.antAufnDatInput.getAttribute('value');
    }

    async setAntEingDatInput(antEingDat) {
        await this.antEingDatInput.sendKeys(antEingDat);
    }

    async getAntEingDatInput() {
        return this.antEingDatInput.getAttribute('value');
    }

    async setAnzahlStrukInput(anzahlStruk) {
        await this.anzahlStrukInput.sendKeys(anzahlStruk);
    }

    async getAnzahlStrukInput() {
        return this.anzahlStrukInput.getAttribute('value');
    }

    async setBtgInput(btg) {
        await this.btgInput.sendKeys(btg);
    }

    async getBtgInput() {
        return this.btgInput.getAttribute('value');
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class VkaDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-vka-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-vka'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
