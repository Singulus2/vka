/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { VkaVkaService } from 'app/entities/vka-vka/vka-vka.service';
import { IVkaVka, VkaVka } from 'app/shared/model/vka-vka.model';

describe('Service Tests', () => {
    describe('VkaVka Service', () => {
        let injector: TestBed;
        let service: VkaVkaService;
        let httpMock: HttpTestingController;
        let elemDefault: IVkaVka;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(VkaVkaService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new VkaVka(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a VkaVka', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new VkaVka(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a VkaVka', async () => {
                const returnedFromService = Object.assign(
                    {
                        vnr: 'BBBBBB',
                        versArt: 'BBBBBB',
                        prioritaet: 'BBBBBB',
                        bearbDat: 'BBBBBB',
                        bearbUhr: 'BBBBBB',
                        status: 'BBBBBB',
                        rd: 'BBBBBB',
                        ges: 'BBBBBB',
                        bza: 'BBBBBB',
                        tarif: 'BBBBBB',
                        tarifUnr1: 'BBBBBB',
                        tarifUnr2: 'BBBBBB',
                        mkt1: 'BBBBBB',
                        bewegSchl: 'BBBBBB',
                        wirkungDat: 'BBBBBB',
                        antAufnDat: 'BBBBBB',
                        antEingDat: 'BBBBBB',
                        anzahlStruk: 'BBBBBB',
                        btg: 1
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of VkaVka', async () => {
                const returnedFromService = Object.assign(
                    {
                        vnr: 'BBBBBB',
                        versArt: 'BBBBBB',
                        prioritaet: 'BBBBBB',
                        bearbDat: 'BBBBBB',
                        bearbUhr: 'BBBBBB',
                        status: 'BBBBBB',
                        rd: 'BBBBBB',
                        ges: 'BBBBBB',
                        bza: 'BBBBBB',
                        tarif: 'BBBBBB',
                        tarifUnr1: 'BBBBBB',
                        tarifUnr2: 'BBBBBB',
                        mkt1: 'BBBBBB',
                        bewegSchl: 'BBBBBB',
                        wirkungDat: 'BBBBBB',
                        antAufnDat: 'BBBBBB',
                        antEingDat: 'BBBBBB',
                        anzahlStruk: 'BBBBBB',
                        btg: 1
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a VkaVka', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
