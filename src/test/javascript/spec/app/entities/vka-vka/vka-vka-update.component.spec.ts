/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { VkaTestModule } from '../../../test.module';
import { VkaVkaUpdateComponent } from 'app/entities/vka-vka/vka-vka-update.component';
import { VkaVkaService } from 'app/entities/vka-vka/vka-vka.service';
import { VkaVka } from 'app/shared/model/vka-vka.model';

describe('Component Tests', () => {
    describe('VkaVka Management Update Component', () => {
        let comp: VkaVkaUpdateComponent;
        let fixture: ComponentFixture<VkaVkaUpdateComponent>;
        let service: VkaVkaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [VkaTestModule],
                declarations: [VkaVkaUpdateComponent]
            })
                .overrideTemplate(VkaVkaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VkaVkaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VkaVkaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new VkaVka(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.vka = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new VkaVka();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.vka = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
