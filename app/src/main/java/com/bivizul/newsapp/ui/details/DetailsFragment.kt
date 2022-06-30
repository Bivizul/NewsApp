package com.bivizul.newsapp.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.newsapp.R
import com.bivizul.newsapp.databinding.FragmentDetailsBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {


    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val bundleArgs: DetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleArg = bundleArgs.article

        with(binding) {
            articleArg.let { article ->
                article.urlToImage.let {
                    Glide.with(this@DetailsFragment).load(article.urlToImage).into(imgHeader)
                }
                imgHeader.clipToOutline = true
                tvArticleDetailsTittle.text = article.title
                tvArticleDetailsDescriptionText.text = article.description

                btArticleDetails.setOnClickListener {
                    try {
                        Intent()
                            .setAction(Intent.ACTION_VIEW)
                            .addCategory(Intent.CATEGORY_BROWSABLE)
                            .setData(Uri.parse(takeIf { URLUtil.isValidUrl(article.url) }
                                ?.let {
                                    article.url
                                } ?: "https://google.com"))
                            .let {
                                ContextCompat.startActivity(requireContext(), it, null)
                            }
                    } catch (e: Exception) {
                        Toast.makeText(context,
                            "The device doesn't have any browser to view the document!",
                            Toast.LENGTH_LONG)
                    }
                }

                iconFavorite.setOnClickListener{
                    viewModel.saveFavoriteArticle(article = article)
                }
            }
        }
    }
}