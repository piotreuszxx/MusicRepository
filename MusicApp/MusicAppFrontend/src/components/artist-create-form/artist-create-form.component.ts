import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {ArtistService} from '../../services/artist-service';
import {ArtistsComponent} from '../artists/artists.component';
import {IArtistRequest, IArtistResponse} from '../../types';


@Component({
  selector: 'app-artist-create-form',
  imports: [ReactiveFormsModule],
  templateUrl: './artist-create-form.component.html',
  styleUrl: './artist-create-form.component.css'
})
export class ArtistCreateFormComponent {
  artistCreateForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private artistService: ArtistService,
    private artistsComponent: ArtistsComponent,
  ) {
    this.artistCreateForm = this.formBuilder.group({
      name: [''],
      surname: [''],
      age: [''],
      stagename: ['']
    });
  }

  onSubmit() {this.createArtist();}

  createArtist() {
    const formData = this.artistCreateForm.value as IArtistRequest;

    this.artistService.create(formData)
    .subscribe({
      next: (data) => {
        const newArtist = data as IArtistResponse;
        this.artistsComponent.onArtistCreated(newArtist);
      },
      error: (err) => {
        console.log("Error creating artist", err)
      }
    })
  }

}
