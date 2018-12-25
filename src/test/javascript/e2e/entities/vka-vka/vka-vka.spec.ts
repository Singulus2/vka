/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { VkaComponentsPage, VkaDeleteDialog, VkaUpdatePage } from './vka-vka.page-object';

const expect = chai.expect;

describe('Vka e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let vkaUpdatePage: VkaUpdatePage;
    let vkaComponentsPage: VkaComponentsPage;
    let vkaDeleteDialog: VkaDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Vkas', async () => {
        await navBarPage.goToEntity('vka-vka');
        vkaComponentsPage = new VkaComponentsPage();
        expect(await vkaComponentsPage.getTitle()).to.eq('vkaApp.vka.home.title');
    });

    it('should load create Vka page', async () => {
        await vkaComponentsPage.clickOnCreateButton();
        vkaUpdatePage = new VkaUpdatePage();
        expect(await vkaUpdatePage.getPageTitle()).to.eq('vkaApp.vka.home.createOrEditLabel');
        await vkaUpdatePage.cancel();
    });

    it('should create and save Vkas', async () => {
        const nbButtonsBeforeCreate = await vkaComponentsPage.countDeleteButtons();

        await vkaComponentsPage.clickOnCreateButton();
        await promise.all([
            vkaUpdatePage.setVnrInput('vnr'),
            vkaUpdatePage.setVersArtInput('versArt'),
            vkaUpdatePage.setPrioritaetInput('prioritaet'),
            vkaUpdatePage.setBearbDatInput('bearbDat'),
            vkaUpdatePage.setBearbUhrInput('bearbUhr'),
            vkaUpdatePage.setStatusInput('status'),
            vkaUpdatePage.setRdInput('rd'),
            vkaUpdatePage.setGesInput('ges'),
            vkaUpdatePage.setBzaInput('bza'),
            vkaUpdatePage.setTarifInput('tarif'),
            vkaUpdatePage.setTarifUnr1Input('tarifUnr1'),
            vkaUpdatePage.setTarifUnr2Input('tarifUnr2'),
            vkaUpdatePage.setMkt1Input('mkt1'),
            vkaUpdatePage.setBewegSchlInput('bewegSchl'),
            vkaUpdatePage.setWirkungDatInput('wirkungDat'),
            vkaUpdatePage.setAntAufnDatInput('antAufnDat'),
            vkaUpdatePage.setAntEingDatInput('antEingDat'),
            vkaUpdatePage.setAnzahlStrukInput('anzahlStruk'),
            vkaUpdatePage.setBtgInput('5')
        ]);
        expect(await vkaUpdatePage.getVnrInput()).to.eq('vnr');
        expect(await vkaUpdatePage.getVersArtInput()).to.eq('versArt');
        expect(await vkaUpdatePage.getPrioritaetInput()).to.eq('prioritaet');
        expect(await vkaUpdatePage.getBearbDatInput()).to.eq('bearbDat');
        expect(await vkaUpdatePage.getBearbUhrInput()).to.eq('bearbUhr');
        expect(await vkaUpdatePage.getStatusInput()).to.eq('status');
        expect(await vkaUpdatePage.getRdInput()).to.eq('rd');
        expect(await vkaUpdatePage.getGesInput()).to.eq('ges');
        expect(await vkaUpdatePage.getBzaInput()).to.eq('bza');
        expect(await vkaUpdatePage.getTarifInput()).to.eq('tarif');
        expect(await vkaUpdatePage.getTarifUnr1Input()).to.eq('tarifUnr1');
        expect(await vkaUpdatePage.getTarifUnr2Input()).to.eq('tarifUnr2');
        expect(await vkaUpdatePage.getMkt1Input()).to.eq('mkt1');
        expect(await vkaUpdatePage.getBewegSchlInput()).to.eq('bewegSchl');
        expect(await vkaUpdatePage.getWirkungDatInput()).to.eq('wirkungDat');
        expect(await vkaUpdatePage.getAntAufnDatInput()).to.eq('antAufnDat');
        expect(await vkaUpdatePage.getAntEingDatInput()).to.eq('antEingDat');
        expect(await vkaUpdatePage.getAnzahlStrukInput()).to.eq('anzahlStruk');
        expect(await vkaUpdatePage.getBtgInput()).to.eq('5');
        await vkaUpdatePage.save();
        expect(await vkaUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await vkaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Vka', async () => {
        const nbButtonsBeforeDelete = await vkaComponentsPage.countDeleteButtons();
        await vkaComponentsPage.clickOnLastDeleteButton();

        vkaDeleteDialog = new VkaDeleteDialog();
        expect(await vkaDeleteDialog.getDialogTitle()).to.eq('vkaApp.vka.delete.question');
        await vkaDeleteDialog.clickOnConfirmButton();

        expect(await vkaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
