import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistCreateFormComponent } from './artist-create-form.component';

describe('ArtistCreateFormComponent', () => {
  let component: ArtistCreateFormComponent;
  let fixture: ComponentFixture<ArtistCreateFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArtistCreateFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistCreateFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
